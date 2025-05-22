package com.twelvefactor.examples.twelvefactornotepad.service;

import com.twelvefactor.examples.twelvefactornotepad.domain.Note;
import com.twelvefactor.examples.twelvefactornotepad.exception.NoteNotFoundException;
import com.twelvefactor.examples.twelvefactornotepad.repository.NoteRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NoteService {
    private final NoteRepository noteRepository;
    private final NotificationService notificationService;

    public NoteService(NoteRepository noteRepository, NotificationService notificationService) {
        this.noteRepository = noteRepository;
        this.notificationService = notificationService;
    }

    @Transactional
    public Note create(Note note) {
        Note savedNote = noteRepository.save(note);
        publishEvent("CREATE", savedNote);
        return savedNote;
    }

    @Transactional(readOnly = true)
    public List<Note> getAll() {
        return noteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Note> getById(Long id) {
        return noteRepository.findById(id);
    }

    @Transactional
    public Note update(Long id, Note noteDetails) {
        Note note = noteRepository
                .findById(id)
                .orElseThrow(() -> new NoteNotFoundException("Note not found with id: " + id));
        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());
        note.setColor(noteDetails.getColor());
        note.setPositionX(noteDetails.getPositionX());
        note.setPositionY(noteDetails.getPositionY());
        Note updatedNote = noteRepository.save(note);
        publishEvent("UPDATE", updatedNote);
        return updatedNote;
    }

    @Transactional
    public void delete(Long id) {
        Note note = noteRepository
                .findById(id)
                .orElseThrow(() -> new NoteNotFoundException("Note not found with id: " + id));
        noteRepository.delete(note);
        notificationService.publishEvent("DELETE", note);
    }

    private void publishEvent(String action, Note note) {
        notificationService.publishEvent(action, note);
    }
}
