package com.xrapalexandra.warehouse.reader;

import com.xrapalexandra.warehouse.model.Book;
import com.xrapalexandra.warehouse.model.PublishHouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BookInputReader {

    private static final Logger logger = LogManager.getLogger(BookInputReader.class);
    private static final String DEFAULT_FILE_NAME = "data/book.txt";
    private static final String WORD_SEPARATOR = " *, *";

    public List<Book> readBooksFromFile() {
        List<Book> books = readBooksFromFile(DEFAULT_FILE_NAME);
        return books;
    }

    public List<Book> readBooksFromFile(String fileName) {
        List<String> contentFromFile = readContentFromFile(fileName);
        List<Book> books= new ArrayList<>();
        for (String bookString : contentFromFile) {
            Book book = createBook(bookString.trim());
            logger.info("Read {} from file.", book);
            books.add(book);
        }
        return books;
    }

    private Book createBook(String bookString) {
        String[] bookFields = bookString.split(WORD_SEPARATOR);
        String name = bookFields[0];
        String author = bookFields[1];
        PublishHouse publishHouse = PublishHouse.valueOf(bookFields[2]);
        int publishingYear = Integer.parseInt(bookFields[3]);
        BigDecimal price = new BigDecimal(bookFields[4]);
        Book book = new Book(name, author, publishHouse, publishingYear, price);
        return book;
    }

    private List<String> readContentFromFile(String fileName) {
        File file = new File(fileName);
        List<String> content= new ArrayList<>();
        try (FileReader fileReader = new FileReader(file)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.add(line);
            }
        } catch (FileNotFoundException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        }
        return content;
    }
}
