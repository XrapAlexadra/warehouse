package com.github.xrapalexandra.reader;

import com.github.xrapalexandra.model.Book;
import com.github.xrapalexandra.model.PublishHouse;
import com.github.xrapalexandra.model.SortingType;
import com.github.xrapalexandra.service.BookService;
import com.github.xrapalexandra.service.impl.BookServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class BookInputReader {

    private static final Logger logger = LogManager.getLogger(BookInputReader.class);
    private static final String DEFAULT_FILE_NAME = "data/book.txt";

    private final BookService bookService = BookServiceImpl.getInstance();

    public void readIntoWarehouse() {
        readIntoWarehouse(DEFAULT_FILE_NAME);
    }

    public void readIntoWarehouse(String fileName) {
        String stringFromFile = readStringFromFile(fileName);
        String[] bookStrings = stringFromFile.split("\n");
        for (String bookString : bookStrings) {
            Book book = createBook(bookString.trim());
            logger.info("Read {} from file.", book);
            bookService.add(book);
        }
    }

    private Book createBook(String bookString) {
        String[] bookFields = bookString.split(" *, *");
        String name = bookFields[0];
        String author = bookFields[1];
        PublishHouse publishHouse = PublishHouse.valueOf(bookFields[2]);
        int publishingYear = Integer.parseInt(bookFields[3]);
        BigDecimal price = new BigDecimal(bookFields[4]);
        Book book = new Book(name, author, publishHouse, publishingYear, price);
        return book;
    }

    private String readStringFromFile(String fileName) {
        File file = new File(fileName);
        StringBuilder stringBuilder = new StringBuilder();

        try (FileReader fileReader = new FileReader(file)) {
            int c;
            while ((c = fileReader.read()) >= 0) {
                stringBuilder.append((char) c);
            }
        } catch (FileNotFoundException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        }
        String result = stringBuilder.toString().trim();
        return result;
    }

    public static void main(String[] args) {
        BookInputReader reader = new BookInputReader();
        reader.readIntoWarehouse();
        List<Book> books = reader.bookService.findAll(SortingType.PRICE, SortingType.PUBLISH_YEAR);
        for (Book book : books) {
            System.out.println(book);
        }
    }
}
