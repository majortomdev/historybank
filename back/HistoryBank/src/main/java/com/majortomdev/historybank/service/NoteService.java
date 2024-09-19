package com.majortomdev.historybank.service;

import com.majortomdev.historybank.entities.Note;
import com.majortomdev.historybank.entities.User;
import com.majortomdev.historybank.repository.NoteRepository;
import com.majortomdev.historybank.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public NoteService(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    public Note createNote(Note note, String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        Note newNote = new Note(note.getTitle(), note.getNotes(), user);
        return noteRepository.save(newNote);
    }

    public List<Note> getNotesForUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        return noteRepository.findByUserId(user.getId());
    }

    public Note updateNote(int noteId, Note noteDetails, String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        if (note.getUser().getId() != user.getId()) {
            throw new RuntimeException("You are not authorized to update this note");
        }

        note.setTitle(noteDetails.getTitle());
        note.setNotes(noteDetails.getNotes());
        return noteRepository.save(note);
    }

    public void deleteNote(int noteId, String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        if (note.getUser().getId() != user.getId()) {
            throw new RuntimeException("You are not authorized to delete this note");
        }

        noteRepository.delete(note);
    }
}
