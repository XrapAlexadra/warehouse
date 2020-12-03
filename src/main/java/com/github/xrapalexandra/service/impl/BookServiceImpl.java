package com.github.xrapalexandra.service.impl;


import com.github.xrapalexandra.comparator.BookComparator;
import com.github.xrapalexandra.comparator.BookComparatorFactory;
import com.github.xrapalexandra.dao.BookDao;
import com.github.xrapalexandra.exeptoin.DaoException;
import com.github.xrapalexandra.dao.impl.BookDaoImpl;
import com.github.xrapalexandra.model.Book;
import com.github.xrapalexandra.model.PublishHouse;
import com.github.xrapalexandra.model.SortingType;
import com.github.xrapalexandra.service.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {

    private static final Logger logger = LogManager.getLogger(BookServiceImpl.class);
    private static BookServiceImpl instance;

    private final BookDao bookDao = BookDaoImpl.getInstance();
    private final BookComparatorFactory bookComparatorFactory = BookComparatorFactory.getInstance();

    private BookServiceImpl() {
    }

    public static BookServiceImpl getInstance() {
        if (instance == null) {
            instance = new BookServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = bookDao.findAll();
        return books;
    }

    @Override
    public List<Book> findAll(SortingType... args) {
        List<Book> books = findAll();
        BookComparator comparator = bookComparatorFactory.create(args);
        sort(books, comparator);
        return books;
    }

    @Override
    public Optional<Book> findById(long id) {
        Optional<Book> book = bookDao.findById(id);
        return book;
    }

    @Override
    public List<Book> findByName(String name) {
        List<Book> books = bookDao.findByName(name);
        return books;
    }

    @Override
    public List<Book> findByPublishHouse(PublishHouse publishHouse) {
        List<Book> books = bookDao.findByPublishHouse(publishHouse);
        return books;
    }

    @Override
    public List<Book> findByAuthor(String author) {
        List<Book> books = bookDao.findByAuthor(author);
        return books;
    }

    @Override
    public boolean delete(Book book) {
        boolean result = false;
        try {
            bookDao.delete(book);
            result = true;
            logger.info("Delete {} from warehouse", book);
        } catch (DaoException e) {
            logger.error(e);
        }
        return result;
    }

    @Override
    public boolean delete(long id) {
        boolean result = false;
        try {
            bookDao.delete(id);
            result = true;
            logger.info("Delete book with id:{} from warehouse", id);
        } catch (DaoException e) {
            logger.error(e);
        }
        return result;
    }

    @Override
    public boolean add(Book book) {
        boolean result = false;
        try {
            bookDao.add(book);
            result = true;
            logger.info("Add {} in warehouse", book);
        } catch (DaoException e) {
            logger.error(e);
        }
        return result;
    }

    @Override
    public boolean update(Book book) {
        boolean result = false;
        try {
            bookDao.update(book);
            result = true;
            logger.info("Update {} in warehouse", book);
        } catch (DaoException e) {
            logger.error(e);
        }
        return result;
    }

    private void sort(List<Book> books, BookComparator comparator) {
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < books.size() -1; i++) {
                if (comparator.compare(books.get(i), books.get(i + 1)) > 0) {
                    isSorted = false;
                    swap(books, i);
                }
            }
        }
    }

    private void swap(List<Book> books, int index) {
        Book temp = books.get(index);
        books.set(index, books.get(index + 1));
        books.set(index + 1, temp);
    }
}
