package com.abhi.bookshackerearth.controller;

import com.abhi.bookshackerearth.dao.Dao;
import com.abhi.bookshackerearth.dao.Repository;
import com.abhi.bookshackerearth.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

//@RestController
//@org.springframework.stereotype.Controller

@org.springframework.stereotype.Controller
@RequestMapping("/api")
public class Controller {

    //declare dependencies of Dao objects which are actually
    @Autowired
    private Dao dao;
    @Autowired
    Repository repository;
    @GetMapping("/api/{bookID}")
        public Book bookPage(@PathVariable int bookID) {
        System.out.println("yes you got in");
        Optional<Book> optional = repository.findById(bookID);
        if(optional.isPresent()) {
            System.out.println(optional.get());
            return optional.get();
        } else {
            System.out.println("There is an error");
        }
        return null;
    }
}
