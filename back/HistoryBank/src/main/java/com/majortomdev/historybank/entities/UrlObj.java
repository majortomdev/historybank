package com.majortomdev.historybank.entities;

import jakarta.persistence.*;


@Entity
public class UrlObj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String url;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Constructors
    public UrlObj() {
    }

    public UrlObj(String url, User user) {
        this.url = url;
        this.user = user;
    }

    public UrlObj(String url) {
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
//public class UrlObj {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    int id = 0;
//    String url;
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    User user;
//}
