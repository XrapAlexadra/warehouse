package com.github.xrapalexandra.dao.impl;

import com.github.xrapalexandra.dao.BookDao;
import com.github.xrapalexandra.exeptoin.DaoException;
import com.github.xrapalexandra.model.Book;
import com.github.xrapalexandra.model.PublishHouse;
import com.github.xrapalexandra.model.Warehouse;
import com.github.xrapalexandra.service.impl.BookServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDaoImpl implements BookDao {

    private static final Logger logger = LogManager.getLogger(BookDaoImpl.class);
    private static BookDaoImpl instance;

    private final Warehouse warehouse = Warehouse.getInstance();

    private BookDaoImpl() {
    }

    public static BookDaoImpl getInstance() {
        if (instance == null) {
            instance = new BookDaoImpl();
        }
        return instance;
    }

    @Override
    public List<Book> findAll() {
        List<Book> result = new ArrayList<>();
        for (int i = 0; i < warehouse.size(); i++) {
            result.add(warehouse.get(i));
        }
        return result;
    }

    @Override
    public Optional<Book> findById(long id) {
        Book book = null;
        int i = 0;
        while (i < warehouse.size()) {
            if (warehouse.get(i).getId() == id) {
                book = warehouse.get(i);
                break;
            }
            i++;
        }
        Optional<Book> result = Optional.ofNullable(book);
        return result;
    }

    @Override
    public List<Book> findByName(String name) {
        List<Book> result = new ArrayList<>();
        for (int i = 0; i < warehouse.size(); i++) {
            Book book = warehouse.get(i);
            if (book.getName().equals(name)) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public List<Book> findByPublishHouse(PublishHouse publishHouse) {
        List<Book> result = new ArrayList<>();
        for (int i = 0; i < warehouse.size(); i++) {
            Book book = warehouse.get(i);
            if (book.getPublishHouse().equals(publishHouse)) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public List<Book> findByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (int i = 0; i < warehouse.size(); i++) {
            Book book = warehouse.get(i);
            if (book.getAuthor().equals(author)) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public void delete(Book book) throws DaoException {
        if (!warehouse.contains(book)) {
            throw new DaoException("There is no " + book + " in warehouse.");
        }
        warehouse.remove(book);
        logger.info("Delete {} from warehouse", book);
    }

    @Override
    public void delete(long id) throws DaoException {
        Optional<Book> book = findById(id);
        if (!book.isPresent()) {
            throw new DaoException("There is no book with id: " + id + " in warehouse.");
        }
        warehouse.remove(book.get());
        logger.info("Delete book with id:{} from warehouse", id);
    }

    @Override
    public void add(Book book) throws DaoException {
        if (warehouse.contains(book)) {
            throw new DaoException(book + " already is exist!");
        }
        warehouse.add(book);
        logger.info("Add {} in warehouse", book);
    }

    @Override
    public void update(Book book) throws DaoException {
        Optional<Book> result = findById(book.getId());
        if (!result.isPresent()) {
            throw new DaoException("There is no " + book + " in warehouse.");
        }
        int index = warehouse.indexOf(book);
        warehouse.set(index, book);
        logger.info("Update {} in warehouse", book);
    }
}
