let stompClient = null;
let notes = new Map(); 
let highestZIndex = 1;
let dragOffsetX, dragOffsetY;
let draggedNote = null;
let isDragging = false;
let noteContainerElement = null;
let containerPageOffsetX = 0;
let containerPageOffsetY = 0;

// Function to calculate and store the note container's offset from the page origin.
function updateContainerOffsets() {
    if (!noteContainerElement) {
        noteContainerElement = document.getElementById('noteContainer');
    }
    if (noteContainerElement) {
        const rect = noteContainerElement.getBoundingClientRect();
        containerPageOffsetX = rect.left + window.scrollX;
        containerPageOffsetY = rect.top + window.scrollY;
    }
}

// Function to create a new note by calling the API
async function createNewNote() {
    console.log("createNewNote called - attempting to POST to API");
    const containerRect = noteContainerElement.getBoundingClientRect();
    const initialRelX = Math.max(20, Math.random() * (containerRect.width - 270));
    const initialRelY = Math.max(20, Math.random() * (containerRect.height - 270));
    const newNotePayload = {
        title: "New Note",
        content: "Click to edit...",
        color: "#ffd700", 
        positionX: initialRelX + containerPageOffsetX, // Send initial position as page-absolute
        positionY: initialRelY + containerPageOffsetY  // Send initial position as page-absolute
    };
    try {
        const response = await fetch('/api/v1/notes', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newNotePayload)
        });
        if (!response.ok) {
            const errorText = await response.text();
            console.error('Failed to create note:', response.status, errorText);
            alert(`Error creating note: ${response.status} - ${errorText}`);
            return;
        }
        const createdNoteFromAPI = await response.json(); // Expecting page-absolute positionX/Y
        console.log("Note created successfully via API, awaiting WebSocket message for UI update:", createdNoteFromAPI);

    } catch (error) {
        console.error('Error posting new note:', error);
        alert(`Error creating note: ${error.message}`);
    }
}

// Function to add a note to the UI
function addNoteToUI(note) {
    if (!noteContainerElement) return;

    // Check if the element already exists to make this function idempotent
    const existingNoteElement = document.getElementById(`note-${note.id}`);
    if (existingNoteElement) {
        console.warn(`addNoteToUI called for note ${note.id} which already exists in the DOM. Calling updateNoteInUI instead.`);
        updateNoteInUI(note); // Ensure the existing note is up-to-date
        return; // Stop further execution to prevent creating a duplicate
    }

    updateContainerOffsets(); // Ensure offsets are fresh if container could have moved (e.g. layout shifts)

    const noteElement = document.createElement('div');
    noteElement.classList.add('note');
    noteElement.id = `note-${note.id}`;
    noteElement.style.left = `${(note.positionX || (50 + containerPageOffsetX)) - containerPageOffsetX}px`;
    noteElement.style.top = `${(note.positionY || (150 + containerPageOffsetY)) - containerPageOffsetY}px`;
    noteElement.style.backgroundColor = note.color || '#ffd700';
    noteElement.style.zIndex = highestZIndex++;

    // Updated innerHTML for correct button placement and structure
    noteElement.innerHTML = `
        <div class="note-header">
            <span class="drag-handle">&#x2630;</span>
            <div class="note-title" contenteditable="true">${note.title || 'Note'}</div>
            <div class="note-actions">
                <button class="delete-btn" title="Delete note">&times;</button>
            </div>
        </div>
        <div class="note-content" contenteditable="true">${note.content || ''}</div>
        <div class="note-controls">
            <div class="color-palette">
                <div class="color-circle" data-color="#ffd700" style="background-color: #ffd700;" title="Yellow"></div>
                <div class="color-circle" data-color="#dc3545" style="background-color: #dc3545;" title="Red"></div>
                <div class="color-circle" data-color="#90ee90" style="background-color: #90ee90;" title="Green"></div>
                <div class="note-timestamp">${note.updatedAt ? new Date(note.updatedAt).toLocaleString() : ''}</div>
            </div>
        </div>
    `;

    // Make draggable
    const dragHandle = noteElement.querySelector('.drag-handle');
    if (dragHandle) {
        dragHandle.addEventListener('mousedown', (e) => {
            if (e.target.closest('button, [contenteditable]')) return;
            isDragging = true;
            draggedNote = noteElement;
            updateContainerOffsets(); 
            highestZIndex++;
            draggedNote.style.zIndex = highestZIndex;
            
            const noteId = parseInt(draggedNote.id.replace('note-', ''));
            const currentNoteData = notes.get(noteId);

            if (currentNoteData && typeof currentNoteData.positionX === 'number' && typeof currentNoteData.positionY === 'number') {
                dragOffsetX = e.pageX - currentNoteData.positionX;
                dragOffsetY = e.pageY - currentNoteData.positionY;
            } else {
                console.warn(`Note ${noteId} data not found or position invalid in notes map at mousedown, using getBoundingClientRect as fallback.`);
                const rect = draggedNote.getBoundingClientRect();
                dragOffsetX = e.pageX - (rect.left + window.scrollX);
                dragOffsetY = e.pageY - (rect.top + window.scrollY);
            }
            
            draggedNote.classList.add('dragging');
            e.preventDefault();
        });
    }

    // Add to container
    const addBtn = document.querySelector('.add-note-btn');
    if (addBtn && addBtn.parentNode === noteContainerElement.parentNode) {
        noteContainerElement.appendChild(noteElement);
    } else {
        noteContainerElement.appendChild(noteElement);
    }

    notes.set(note.id, note);

    const colorCircles = noteElement.querySelectorAll('.color-circle');
    colorCircles.forEach(circle => {
        circle.addEventListener('click', () => {
            const newColor = circle.getAttribute('data-color');
            noteElement.style.backgroundColor = newColor;
            updateNoteColorOnServer(note.id, newColor);
        });
    });

    const deleteButton = noteElement.querySelector('.delete-btn');
    if (deleteButton) {
        deleteButton.addEventListener('click', async () => {
            console.log(`Attempting to delete note ${note.id} via API`);
            try {
                const response = await fetch(`/api/v1/notes/${note.id}`, {
                    method: 'DELETE'
                });
                if (response.ok) {
                    console.log(`Note ${note.id} delete request successful. Awaiting WebSocket confirmation.`);
                } else {
                    const errorText = await response.text();
                    console.error(`Failed to delete note ${note.id}:`, response.status, errorText);
                    alert(`Error deleting note: ${response.status} - ${errorText}`);
                }
            } catch (error) {
                console.error(`Error during delete API call for note ${note.id}:`, error);
                alert(`Error deleting note: ${error.message}`);
            }
        });
    }

    const titleElement = noteElement.querySelector('.note-title');
    const contentElement = noteElement.querySelector('.note-content');

    async function updateNoteField(noteId, fieldName, newValue) {
        const currentNoteData = notes.get(noteId);
        if (!currentNoteData) {
            console.error(`Cannot update ${fieldName}, note data not found for id: ${noteId}`);
            return;
        }
        if (fieldName === 'title' && currentNoteData.title === newValue) return;
        if (fieldName === 'content' && currentNoteData.content === newValue) return;
        const updatePayload = { ...currentNoteData, [fieldName]: newValue };
        console.log(`Note ${noteId} ${fieldName} changed. Attempting to update via API.`);
        try {
            const response = await fetch(`/api/v1/notes/${noteId}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(updatePayload)
            });
            if (response.ok) {
                const updatedNoteFromServer = await response.json();
                notes.set(noteId, updatedNoteFromServer);
                console.log(`Note ${noteId} ${fieldName} updated successfully:`, updatedNoteFromServer);
            } else {
                const errorText = await response.text();
                console.error(`Failed to update note ${noteId} ${fieldName}:`, response.status, errorText);
                alert(`Error updating note ${fieldName}: ${response.status} - ${errorText}`);
                if (fieldName === 'title') titleElement.textContent = currentNoteData.title;
                if (fieldName === 'content') contentElement.innerHTML = currentNoteData.content;
            }
        } catch (error) {
            console.error(`Error during API call for note ${noteId} ${fieldName} update:`, error);
            alert(`Error updating note ${fieldName}: ${error.message}`);
        }
    }

    if (titleElement) {
        titleElement.addEventListener('blur', () => {
            updateNoteField(note.id, 'title', titleElement.textContent);
        });
    }

    if (contentElement) {
        contentElement.addEventListener('blur', () => {
            updateNoteField(note.id, 'content', contentElement.innerHTML);
        });
    }
}

// Function to remove a note from the UI by ID
function removeNoteFromUI(noteId) {
    const noteElement = document.getElementById(`note-${noteId}`);
    if (noteElement) {
        noteElement.remove();
        console.log(`Note ${noteId} removed from UI via WebSocket.`);
    } else {
        console.log(`Note ${noteId} not found in UI for removal (possibly already removed).`);
    }
    if (notes.has(noteId)) {
        notes.delete(noteId);
        console.log(`Note ${noteId} removed from local notes map.`);
    } else {
        console.log(`Note ${noteId} was not in local notes map for removal.`);
    }
}

// Function to update an existing note in the UI
function updateNoteInUI(note) {
    const noteElement = document.getElementById(`note-${note.id}`);
    updateContainerOffsets(); 
    if (noteElement) {
        noteElement.style.left = `${note.positionX - containerPageOffsetX}px`;
        noteElement.style.top = `${note.positionY - containerPageOffsetY}px`;
        noteElement.style.backgroundColor = note.color || '#ffd700';        
        const titleElement = noteElement.querySelector('.note-title');
        if (titleElement) titleElement.textContent = note.title || 'Note';        
        const contentElement = noteElement.querySelector('.note-content');
        if (contentElement) contentElement.innerHTML = note.content || '';
        const timestampElement = noteElement.querySelector('.note-timestamp');
        if (timestampElement) {
            timestampElement.textContent = note.updatedAt ? new Date(note.updatedAt).toLocaleString() : '';
        }
        notes.set(note.id, note); 
        console.log(`Note ${note.id} updated in UI via WebSocket.`);
    } else {
        console.warn(`Attempted to update note ${note.id}, but element not found. Adding it instead.`);
        addNoteToUI(note);
    }
}

document.addEventListener('mousemove', (e) => {
    if (!isDragging || !draggedNote) return;
    updateContainerOffsets(); // Ensure offsets are current during drag for accurate conversion

    let targetPageX = e.pageX - dragOffsetX;
    let targetPageY = e.pageY - dragOffsetY;

    const noteRect = draggedNote.getBoundingClientRect();
    const documentWidth = document.documentElement.scrollWidth;
    const documentHeight = document.documentElement.scrollHeight;

    targetPageX = Math.max(0, Math.min(targetPageX, documentWidth - noteRect.width));
    targetPageY = Math.max(0, Math.min(targetPageY, documentHeight - noteRect.height));

    let styleLeft = targetPageX - containerPageOffsetX;
    let styleTop = targetPageY - containerPageOffsetY;

    draggedNote.style.left = `${styleLeft}px`;
    draggedNote.style.top = `${styleTop}px`;

    // Store the page-absolute coordinates on the element for mouseup
    draggedNote.dataset.targetPageX = targetPageX;
    draggedNote.dataset.targetPageY = targetPageY;
});

document.addEventListener('mouseup', async (e) => {
    if (isDragging && draggedNote) {
        draggedNote.classList.remove('dragging');
        
        const noteId = parseInt(draggedNote.id.replace('note-', ''));
        const currentNoteData = notes.get(noteId);

        // Retrieve the final page-absolute coordinates stored during mousemove
        const finalPageX = parseFloat(draggedNote.dataset.targetPageX);
        const finalPageY = parseFloat(draggedNote.dataset.targetPageY);

        if (currentNoteData && (currentNoteData.positionX !== finalPageX || currentNoteData.positionY !== finalPageY)) {
            const updatePayload = {
                ...currentNoteData,
                positionX: finalPageX, // Send page-absolute
                positionY: finalPageY  // Send page-absolute
            };
            
            console.log(`Note ${noteId} moved. Attempting to update position via API to X: ${finalPageX}, Y: ${finalPageY}`);
            try {
                const response = await fetch(`/api/v1/notes/${noteId}`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(updatePayload) 
                });
                if (response.ok) {
                    const updatedNoteFromServer = await response.json(); // Expects page-absolute X/Y
                    notes.set(noteId, updatedNoteFromServer);
                    // UI update will be handled by WebSocket broadcast via updateNoteInUI
                    console.log(`Note ${noteId} position update request sent successfully.`);
                } else {
                    const errorText = await response.text();
                    console.error(`Failed to update note ${noteId} position:`, response.status, errorText);
                    alert(`Error updating note position: ${response.status} - ${errorText}`);
                    // Revert visual position if API call fails
                    // This needs to convert currentNoteData.positionX/Y (page-absolute) back to styleLeft/Top
                    updateContainerOffsets(); // Ensure offsets are fresh for revert calculation
                    draggedNote.style.left = `${currentNoteData.positionX - containerPageOffsetX}px`;
                    draggedNote.style.top = `${currentNoteData.positionY - containerPageOffsetY}px`;
                }
            } catch (error) {
                console.error(`Error during API call for note ${noteId} position update:`, error);
                alert(`Error updating note position: ${error.message}`);
                updateContainerOffsets();
                draggedNote.style.left = `${currentNoteData.positionX - containerPageOffsetX}px`;
                draggedNote.style.top = `${currentNoteData.positionY - containerPageOffsetY}px`;
            }
        }
    }
    isDragging = false;
    draggedNote = null;
});

async function fetchAndDisplayNotes() {
    updateContainerOffsets(); // Ensure offsets are known before fetching and displaying
    try {
        const response = await fetch('/api/v1/notes');
        if (!response.ok) {
            console.error('Failed to fetch notes:', response.status, await response.text());
            return;
        }
        const notesFromAPI = await response.json(); // Expecting page-absolute X/Y
        if (notesFromAPI && notesFromAPI.length > 0) {
            notesFromAPI.forEach(note => {
                addNoteToUI(note);
            });
        } else {
            console.log('No notes found from API or empty response.');
        }
    } catch (error) {
        console.error('Error fetching or displaying notes:', error);
    }
}

function connectWebSocket() {
    const socket = new SockJS('/ws-notepad');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, (frame) => {
        console.log('Connected to WebSocket: ' + frame);
        stompClient.subscribe('/topic/notes', (message) => {
            const wsMessage = JSON.parse(message.body);
            console.log('Received WebSocket message:', wsMessage);

            if (wsMessage.processedByHost) {
                console.log(`Event for note ${wsMessage.payload.id} (Action: ${wsMessage.action}) processed by host: ${wsMessage.processedByHost}`);
            }

            switch (wsMessage.action) {
                case 'CREATE':
                    if (notes.has(wsMessage.payload.id)) {
                        updateNoteInUI(wsMessage.payload);
                    } else {
                        addNoteToUI(wsMessage.payload);
                    }
                    break;
                case 'UPDATE':
                    updateNoteInUI(wsMessage.payload);
                    break;
                case 'DELETE':
                    removeNoteFromUI(wsMessage.payload.id); // Ensure we pass the ID
                    break;
                default:
                    console.warn('Unknown WebSocket action:', wsMessage.action);
            }
        });
    }, function(error) {
        console.error('STOMP error: ' + error);
        setTimeout(connectWebSocket, 10000); // Simple reconnect attempt after 10 seconds
    });
}

// Initial fetch and display of notes, then connect WebSocket
document.addEventListener('DOMContentLoaded', async () => {
    noteContainerElement = document.getElementById('noteContainer');
    updateContainerOffsets(); // Initial calculation
    await fetchAndDisplayNotes();
    connectWebSocket(); 
}); 

// New function to update note color on the server
async function updateNoteColorOnServer(noteId, newColor) {
    const currentNoteData = notes.get(noteId);
    if (!currentNoteData) {
        console.error(`Cannot update color, note data not found for id: ${noteId}`);
        return;
    }

    // Avoid API call if color hasn't changed
    if (currentNoteData.color === newColor) return;

    const updatePayload = {
        ...currentNoteData,
        color: newColor
    };

    console.log(`Note ${noteId} color changed to ${newColor}. Attempting to update via API.`);

    try {
        const response = await fetch(`/api/v1/notes/${noteId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(updatePayload)
        });

        if (response.ok) {
            const updatedNote = await response.json();
            notes.set(noteId, updatedNote); // Update local cache with server response
            console.log(`Note ${noteId} color updated successfully on server.`);
        } else {
            const errorText = await response.text();
            console.error(`Failed to update note ${noteId} color:`, response.status, errorText);
            alert(`Error updating note color: ${response.status} - ${errorText}`);
        }
    } catch (error) {
        console.error(`Error during color update API call for note ${noteId}:`, error);
        alert(`Error updating note color: ${error.message}`);
    }
} 
