package com.abhi.bookshackerearth.controller;

import com.abhi.bookshackerearth.dao.Dao;
import com.abhi.bookshackerearth.dao.Repository;
import com.abhi.bookshackerearth.entity.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@RestController
//@org.springframework.stereotype.Controller

//@org.springframework.stereotype.Controller
@RequestMapping("/api")
@RestController //since this is rest controller and jackson is added in dependency, the output on web is JSON format
public class Controller {

    //declare dependencies of Dao objects which are actually accessing the database
    @Autowired
    private Dao dao;
    @Autowired
    Repository repository;

    //declare and define listeners so that output can be received from server
    @GetMapping("/api/{bookID}")
    //path variable required to receive data from the database
        public Book bookPage(@PathVariable int bookID) {
        System.out.println("fetching your book");
        //Optional is returned by the default methods available in CrudRepository interface
        Optional<Book> optional = repository.findById(bookID);
        if(optional.isPresent()) { //if any entry is found then run below code
            System.out.println(optional.get()); //get() returns the value if its present which in this case should be
            //the book object
            return optional.get();
        } else {
            System.out.println("There is an error");
        }
        return null;
    }

    //declare and define fuzzy search listeners so that output can be received from server
    @GetMapping("/api/title")
    //using path variable can be a little confusing as springboot controller thinks the above getId program can be called as well
    //by using requestparam we have segregated the two calls for the dispatcher
    public String searchBookByTitleFuzzy(@RequestParam(value="title") String title) {
        System.out.println("fetching your books");
        //since we are returning list,we save the result in a list
        List<Book> optional = dao.fuzzySearch(title);
        //fuzzysearch functioning is provided in the dao class
        if(optional.isEmpty()) { //if no entry is found, we run below code
            System.out.println("nothing to return here");
            return null;
        } else {
            //mapper allows us to convert the List to a JSON array
            //try catch block is used to check for any exceptions
            //also its mandatory to either use a catch block or throw in method declaration
            ObjectMapper mapper = new ObjectMapper();
            try {
                return mapper.writeValueAsString(optional);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
