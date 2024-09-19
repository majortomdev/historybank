package com.majortomdev.historybank.controllers;

import com.majortomdev.historybank.dto.NoteDto;
import com.majortomdev.historybank.entities.Note;
import com.majortomdev.historybank.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody NoteDto noteDto, Principal principal) {
        Note note = noteService.createNote(new Note(noteDto.getTitle(), noteDto.getNotes()), principal.getName());
        return ResponseEntity.ok(note);
    }

    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes(Principal principal) {
        List<Note> notes = noteService.getNotesForUser(principal.getName());
        return ResponseEntity.ok(notes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(
            @PathVariable int id,
            @RequestBody NoteDto noteDto,
            Principal principal) {
        Note updatedNote = noteService.updateNote(id, new Note(noteDto.getTitle(), noteDto.getNotes()), principal.getName());
        return ResponseEntity.ok(updatedNote);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable int id, Principal principal) {
        noteService.deleteNote(id, principal.getName());
        return ResponseEntity.noContent().build();
    }
}