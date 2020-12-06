package com.xrapalexandra.warehouse.comparator;

import com.xrapalexandra.warehouse.model.Book;

import java.util.Comparator;

public class BookPublishingYearComparator implements Comparator<Book> {

    @Override
    public int compare(Book book1, Book book2) {
        return Integer.compare(book1.getPublishingYear(), book2.getPublishingYear());
    }
}
