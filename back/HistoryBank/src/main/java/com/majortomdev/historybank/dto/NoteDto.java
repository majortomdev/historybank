package com.majortomdev.historybank.dto;

import java.util.List;

public class NoteDto {

    private String title;
    private List<String> notes;

    // Constructors
    public NoteDto() {
    }

    public NoteDto(String title, List<String> notes) {
        this.title = title;
        this.notes = notes;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getNotes() {
        return notes;
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }
}
