package com.github.xrapalexandra.service;

import com.github.xrapalexandra.model.SortingType;
import com.github.xrapalexandra.exeptoin.DaoException;
import com.github.xrapalexandra.model.Book;
import com.github.xrapalexandra.model.PublishHouse;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    List<Book> findAll(SortingType... args);

    Optional<Book> findById(long id);

    List<Book> findByName(String name);

    List<Book> findByPublishHouse(PublishHouse publishHouse);

    List<Book> findByAuthor(String author);

    boolean delete(Book book);

    boolean delete(long id);

    boolean add(Book book);

    boolean update(Book book);
}
