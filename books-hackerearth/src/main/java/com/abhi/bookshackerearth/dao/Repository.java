package com.abhi.bookshackerearth.dao;

import com.abhi.bookshackerearth.entity.Book;
import org.springframework.data.repository.CrudRepository;

@org.springframework.stereotype.Repository
public interface Repository extends CrudRepository<Book, Integer> {
}
