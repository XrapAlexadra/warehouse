package com.xrapalexandra.warehouse.reader;

import com.xrapalexandra.warehouse.model.Book;
import com.xrapalexandra.warehouse.model.PublishHouse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BookInputReaderTest extends Assert {

    BookInputReader bookInputReader = new BookInputReader();
    List<Book> books = new ArrayList<>();

    @BeforeClass
    public void beforeClass() {
        books.add(new Book("Алые паруса", "Грин А.", PublishHouse.CLEVER, 1984, new BigDecimal(52)));
        books.add(new Book("Алые паруса", "Грин А.", PublishHouse.EXMO, 1992, new BigDecimal(64)));
        books.add(new Book("Идиот", "Достоевский Ф.", PublishHouse.DELIBRI, 1993, new BigDecimal(44)));
        books.add(new Book("Рамзес", "Блок А.", PublishHouse.EXMO, 2001, new BigDecimal(35)));
        books.add(new Book("Сказки", "Пушкин А.", PublishHouse.CLEVER, 2011, new BigDecimal(83)));
        books.add(new Book("Стихи", "Пушкин А.", PublishHouse.EXMO, 2015, new BigDecimal(44)));
        books.add(new Book("Стихи", "Блок А.", PublishHouse.DELIBRI, 2000, new BigDecimal(47)));
        books.add(new Book("Бесы", "Достоевский Ф.", PublishHouse.DELIBRI, 2001, new BigDecimal(64)));
        books.add(new Book("Преступление и наказание", "Достоевский Ф.", PublishHouse.EXMO, 1992, new BigDecimal(52)));
        books.add(new Book("Остров Рено", "Грин А.", PublishHouse.DELIBRI, 2011, new BigDecimal(83)));
    }

    @Test
    public void testReadBooksFromFile() {
        List<Book> actual = bookInputReader.readBooksFromFile();
        List<Book> expected = books;

        assertEquals(actual, expected);
    }
}