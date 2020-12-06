package com.xrapalexandra.warehouse.dao;

import com.xrapalexandra.warehouse.exceptoin.DaoException;
import com.xrapalexandra.warehouse.model.Book;
import com.xrapalexandra.warehouse.model.PublishHouse;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    List<Book> findAll();

    Optional<Book> findById(long id);

    List<Book> findByName(String name);

    List<Book> findByPublishHouse(PublishHouse publishHouse);

    List<Book> findByAuthor(String author);

    void delete(Book book) throws DaoException;

    void delete(long id) throws DaoException;

    void add(Book book) throws DaoException;

    void update(Book book) throws DaoException;
}
