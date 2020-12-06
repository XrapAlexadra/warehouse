package com.xrapalexandra.warehouse.comparator;

import com.xrapalexandra.warehouse.model.Book;

import java.util.Comparator;

public class BookAuthorComparator implements Comparator<Book> {

    @Override
    public int compare(Book book1, Book book2) {
        return book1.getAuthor().compareTo(book2.getAuthor());
    }
}
