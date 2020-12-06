package com.xrapalexandra.warehouse.comparator;

import com.xrapalexandra.warehouse.model.Book;

import java.util.Comparator;

public class BookIdComparator implements Comparator<Book> {

    @Override
    public int compare(Book book1, Book book2) {
        return Long.compare(book1.getId(), book2.getId());
    }
}
