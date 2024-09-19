package com.majortomdev.historybank.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @ElementCollection
    private List<String> notes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Constructors
    public Note() {
    }

    public Note(String title, List<String> notes, User user) {
        this.title = title;
        this.notes = notes;
        this.user = user;
    }

    public Note(String title, List<String> notes) {

    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}




















//import jakarta.persistence.*;
//
//import java.util.List;
//
//@Entity
//public class Note {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    int id = 0;
//    String title;
//    @ElementCollection
//    List<String> lines;
//    User user;
//
//}
