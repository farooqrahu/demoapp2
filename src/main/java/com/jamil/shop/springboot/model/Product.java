package com.jamil.shop.springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;


@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String duration;
    private String date;
    private String album;

    @OneToOne
    @JoinColumn(name = "content")
    private Content content;

    @ManyToOne
    @JoinColumn(name = "author")
    private Author author;

    @JsonIgnore
    @ManyToMany(mappedBy = "productList")
    private List<User> userList;

    public Product() {
    }

    public Product(String name, Author author, String duration, String date, String album, Content content) {
        this.name = name;
        this.author = author;
        this.duration = duration;
        this.date = date;
        this.album = album;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

}
