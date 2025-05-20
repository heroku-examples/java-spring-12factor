package com.twelvefactor.examples.twelvefactornotepad.controller;

import com.twelvefactor.examples.twelvefactornotepad.domain.Note;
import com.twelvefactor.examples.twelvefactornotepad.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notes")
@Tag(name = "Notes API", description = "API for managing notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @Operation(summary = "Get all notes", description = "Retrieves a list of all notes.")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Successfully retrieved list of notes",
                        content =
                                @Content(mediaType = "application/json", schema = @Schema(implementation = Note.class)))
            })
    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        return ResponseEntity.ok(noteService.getAll());
    }

    @Operation(summary = "Get a note by ID", description = "Retrieves a specific note by its ID.")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Successfully retrieved the note",
                        content =
                                @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = Note.class))),
                @ApiResponse(responseCode = "404", description = "Note not found", content = @Content)
            })
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(
            @Parameter(description = "ID of the note to retrieve", required = true, example = "1") @PathVariable
                    Long id) {
        Note note = noteService
                .getById(id)
                .orElseThrow(() -> new com.twelvefactor.examples.twelvefactornotepad.exception.NoteNotFoundException(
                        "Note not found with id: " + id));
        return ResponseEntity.ok(note);
    }

    @Operation(summary = "Create a new note", description = "Creates a new note.")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "201",
                        description = "Note created successfully",
                        content =
                                @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = Note.class))),
                @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
            })
    @PostMapping
    public ResponseEntity<Note> createNote(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                            description =
                                    "Note object to be created. The 'id', 'createdAt', and 'updatedAt' fields are ignored.",
                            required = true,
                            content = @Content(schema = @Schema(implementation = Note.class)))
                    @RequestBody
                    Note note) {
        Note createdNote = noteService.create(note);
        return new ResponseEntity<>(createdNote, HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing note", description = "Updates an existing note by its ID.")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Note updated successfully",
                        content =
                                @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = Note.class))),
                @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
                @ApiResponse(responseCode = "404", description = "Note not found", content = @Content)
            })
    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(
            @Parameter(description = "ID of the note to update", required = true, example = "1") @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                            description =
                                    "Updated note object. The 'id', 'createdAt', and 'updatedAt' fields are ignored or used for optimistic locking if implemented.",
                            required = true,
                            content = @Content(schema = @Schema(implementation = Note.class)))
                    @RequestBody
                    Note noteDetails) {
        Note updatedNote = noteService.update(id, noteDetails);
        return ResponseEntity.ok(updatedNote);
    }

    @Operation(summary = "Delete a note by ID", description = "Deletes a specific note by its ID.")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "204", description = "Note deleted successfully"),
                @ApiResponse(responseCode = "404", description = "Note not found", content = @Content)
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(
            @Parameter(description = "ID of the note to delete", required = true, example = "1") @PathVariable
                    Long id) {
        noteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
