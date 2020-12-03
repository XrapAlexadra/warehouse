package com.github.xrapalexandra.dao;

import com.github.xrapalexandra.dao.impl.BookDaoImpl;
import com.github.xrapalexandra.exeptoin.DaoException;
import com.github.xrapalexandra.model.Book;
import com.github.xrapalexandra.model.PublishHouse;
import com.github.xrapalexandra.warehouse.Warehouse;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDaoTest extends Assert {

    BookDao bookDao = BookDaoImpl.getInstance();
    Warehouse warehouse = Warehouse.getInstance();

    @BeforeMethod
    public void setUp() {
        warehouse.add(new Book(
                "Алые паруса", "Грин А.", PublishHouse.CLEVER, 1984, new BigDecimal(52)
        ));
        warehouse.add(new Book(
                "Алые паруса", "Грин А.", PublishHouse.EXMO, 1992, new BigDecimal(64)
        ));
        warehouse.add(new Book(
                "Идиот", "Достоевский Ф.", PublishHouse.DELIBRI, 1993, new BigDecimal(44)
        ));
        warehouse.add(new Book(
                "Рамзес", "Блок А.", PublishHouse.EXMO, 2001, new BigDecimal(35)
        ));
        warehouse.add(new Book(
                "Сказки", "Пушкин А.", PublishHouse.CLEVER, 2011, new BigDecimal(83)
        ));
    }

    @Test
    public void testFindAll() {
        List<Book> actual = bookDao.findAll();
        List<Book> expected = new ArrayList<>();
        for (int i = 0; i < warehouse.size(); i++) {
            expected.add(warehouse.get(i));
        }

        assertEquals(actual, expected);
    }

    @Test
    public void testFindById() {
        long id = warehouse.get(0).getId();
        Optional<Book> book = bookDao.findById(id);
        Book actual = book.orElse(null);
        Book expected = warehouse.get(0);

        assertEquals(actual, expected);
    }

    @Test
    public void testFindByName() {
        List<Book> actual = bookDao.findByName("Алые паруса");
        List<Book> expected = new ArrayList<>();
        expected.add(warehouse.get(0));
        expected.add(warehouse.get(1));

        assertEquals(actual, expected);
    }

    @Test
    public void testFindByPublishHouse() {
        List<Book> actual = bookDao.findByPublishHouse(PublishHouse.CLEVER);
        List<Book> expected = new ArrayList<>();
        expected.add(warehouse.get(0));
        expected.add(warehouse.get(4));

        assertEquals(actual, expected);
    }

    @Test
    public void testFindByAuthor() {
        List<Book> actual = bookDao.findByAuthor("Блок А.");
        List<Book> expected = new ArrayList<>();
        expected.add(warehouse.get(3));

        assertEquals(actual, expected);
    }

    @Test
    public void testDelete() throws DaoException {
        Book book = warehouse.get(2);
        bookDao.delete(book);

        int actual = warehouse.size();
        int expected = 4;

        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = DaoException.class)
    public void testDeleteException() throws DaoException {
        Book book = new Book(
                "Идиот", "Достоевский Ф.", PublishHouse.DELIBRI, 2011, new BigDecimal(75)
        );
        bookDao.delete(book);
    }

    @Test
    public void testDeleteById() throws DaoException {
        Book book = warehouse.get(2);
        bookDao.delete(book.getId());

        int actual = warehouse.size();
        int expected = 4;

        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = DaoException.class)
    public void testDeleteByIdException() throws DaoException {
        Book book = new Book(
                "Идиот", "Ф.Достоевский", PublishHouse.DELIBRI, 2011, new BigDecimal(75)
        );
        bookDao.delete(book.getId());
    }

    @Test
    public void testAdd() throws DaoException {
        Book book = new Book(
                "Идиот", "Достоевский Ф.", PublishHouse.DELIBRI, 2011, new BigDecimal(75)
        );
        bookDao.add(book);

        Optional<Book> actual = bookDao.findById(book.getId());
        Optional<Book> expected = Optional.of(book);

        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = DaoException.class)
    public void testAddException() throws DaoException {
        Book book = warehouse.get(3);
        bookDao.add(book);
    }

    @Test
    public void testUpdate() throws DaoException {
        Book book = warehouse.get(0);
        book.setPrice(new BigDecimal(48));
        bookDao.update(book);
        Book result = bookDao.findById(book.getId()).orElse(null);

        BigDecimal actual = result.getPrice();
        BigDecimal expected = new BigDecimal(48);

        assertEquals(actual, expected);
    }

    @Test(expectedExceptions = DaoException.class)
    public void testUpdateException() throws DaoException {
        Book book = new Book(
                "Идиот", "Достоевский Ф.", PublishHouse.DELIBRI, 2011, new BigDecimal(75)
        );

        bookDao.update(book);
    }

    @AfterMethod
    public void tearDown() {
        warehouse.clear();
    }
}