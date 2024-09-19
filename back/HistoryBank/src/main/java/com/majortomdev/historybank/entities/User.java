package com.majortomdev.historybank.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UrlObj> urls;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Note> notes;

    // Constructors
    public User() {
    }

    public User(String username, String password, List<UrlObj> urls, List<Note> notes) {
        this.username = username;
        this.password = password;
        this.urls = urls;
        this.notes = notes;
    }

    public User(String username, String encodedPassword) {

    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UrlObj> getUrls() {
        return urls;
    }

    public void setUrls(List<UrlObj> urls) {
        this.urls = urls;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}





//import jakarta.persistence.*;
//
//import java.util.List;
//
//@Entity
//public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    int id = 0;
//    String username;
//    String password;
//    //@OneToMany(mappedBy = "user");
//    List<UrlObj> urls;
//    List<Note> notes;
//
//}
