package com.xrapalexandra.warehouse.service;

import com.xrapalexandra.warehouse.model.Book;
import com.xrapalexandra.warehouse.model.PublishHouse;
import com.xrapalexandra.warehouse.model.SortingType;
import com.xrapalexandra.warehouse.model.Warehouse;
import com.xrapalexandra.warehouse.service.impl.BookServiceImpl;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class BookServiceImplTest extends Assert {

    BookService bookService = BookServiceImpl.getInstance();
    Warehouse warehouse = Warehouse.getInstance();

    @BeforeMethod
    public void setUp() {
        warehouse.add(new Book("Алые паруса", "Грин А.", PublishHouse.CLEVER, 1984, new BigDecimal(52)));
        warehouse.add(new Book("Алые паруса", "Грин А.", PublishHouse.EXMO, 1992, new BigDecimal(64)));
        warehouse.add(new Book("Идиот", "Достоевский Ф.", PublishHouse.DELIBRI, 1993, new BigDecimal(44)));
        warehouse.add(new Book("Рамзес", "Блок А.", PublishHouse.EXMO, 2001, new BigDecimal(35)));
        warehouse.add(new Book("Сказки", "Пушкин А.", PublishHouse.CLEVER, 2011, new BigDecimal(83)));
    }

    @Test
    public void testFindAll() {
        List<Book> actual = bookService.findAll();
        List<Book> expected = new ArrayList<>();
        for (int i = 0; i < warehouse.size(); i++) {
            expected.add(warehouse.get(i));
        }

        assertEquals(actual, expected);
    }

    @Test
    public void testFindById() {
        long id = warehouse.get(0).getId();
        Optional<Book> book = bookService.findById(id);
        Book actual = book.orElse(null);
        Book expected = warehouse.get(0);

        assertEquals(actual, expected);
    }

    @Test
    public void testFindByName() {
        List<Book> actual = bookService.findByName("Алые паруса");
        List<Book> expected = new ArrayList<>();
        expected.add(warehouse.get(0));
        expected.add(warehouse.get(1));

        assertEquals(actual, expected);
    }

    @Test
    public void testFindByPublishHouse() {
        List<Book> actual = bookService.findByPublishHouse(PublishHouse.CLEVER);
        List<Book> expected = new ArrayList<>();
        expected.add(warehouse.get(0));
        expected.add(warehouse.get(4));

        assertEquals(actual, expected);
    }

    @Test
    public void testFindByAuthor() {
        List<Book> actual = bookService.findByAuthor("Блок А.");
        List<Book> expected = new ArrayList<>();
        expected.add(warehouse.get(3));

        assertEquals(actual, expected);
    }

    @Test
    public void testFindAllSort() {
        List<Book> books = new ArrayList<>();
        books.add(warehouse.get(3));
        books.add(warehouse.get(0));
        books.add(warehouse.get(1));
        books.add(warehouse.get(2));
        books.add(warehouse.get(4));

        List<Book> actual = bookService.findAll(SortingType.AUTHOR, SortingType.PUBLISH_YEAR);
        List<Book> expected = books;

        assertEquals(actual, expected);
    }

    @Test
    public void testDelete() {
        Book book = warehouse.get(2);
        bookService.delete(book);

        int actual = warehouse.size();
        int expected = 4;

        assertEquals(actual, expected);
    }

    @Test()
    public void testDeleteFalse() {
        Book book = new Book("Идиот", "Достоевский Ф.", PublishHouse.DELIBRI, 2011, new BigDecimal(75));
        boolean actual = bookService.delete(book);

        assertFalse(actual);
    }

    @Test
    public void testDeleteById() {
        Book book = warehouse.get(2);
        bookService.delete(book.getId());

        int actual = warehouse.size();
        int expected = 4;

        assertEquals(actual, expected);
    }

    @Test()
    public void testDeleteByIdFalse() {
        Book book = new Book("Идиот", "Ф.Достоевский", PublishHouse.DELIBRI, 2011, new BigDecimal(75));
        boolean actual = bookService.delete(book.getId());

        assertFalse(actual);
    }

    @Test
    public void testAdd() {
        Book book = new Book("Идиот", "Достоевский Ф.", PublishHouse.DELIBRI, 2011, new BigDecimal(75));
        bookService.add(book);

        Optional<Book> actual = bookService.findById(book.getId());
        Optional<Book> expected = Optional.of(book);

        assertEquals(actual, expected);
    }

    @Test()
    public void testAddException() {
        Book book = warehouse.get(3);
        boolean actual = bookService.add(book);

        assertFalse(actual);
    }

    @Test
    public void testUpdate() {
        Book book = warehouse.get(0);
        book.setPrice(new BigDecimal(48));
        bookService.update(book);
        Book result = bookService.findById(book.getId()).orElse(null);

        BigDecimal actual = result.getPrice();
        BigDecimal expected = new BigDecimal(48);

        assertEquals(actual, expected);
    }

    @Test()
    public void testUpdateFalse() {
        Book book = new Book("Идиот", "Достоевский Ф.", PublishHouse.DELIBRI, 2011, new BigDecimal(75));
        boolean actual = bookService.update(book);

        assertFalse(actual);
    }

    @AfterMethod
    public void tearDown() {
        warehouse.clear();
    }
}