package com.jamil.shop.springboot.controller;

import com.jamil.shop.springboot.model.Author;
import com.jamil.shop.springboot.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @RequestMapping("/authors")
    public List<Author> getAuthor() {
        return authorService.getAllAuthor();
    }

    @RequestMapping(value = "/author/add", method = RequestMethod.POST)
    public void addAuthor(@RequestBody Author author) {
        authorService.saveAuthor(author);
    }
}
