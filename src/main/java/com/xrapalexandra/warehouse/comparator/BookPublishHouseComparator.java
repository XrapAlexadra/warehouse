package com.xrapalexandra.warehouse.comparator;

import com.xrapalexandra.warehouse.model.Book;

import java.util.Comparator;

public class BookPublishHouseComparator implements Comparator<Book> {

    @Override
    public int compare(Book book1, Book book2) {
        String publishHouse1 = String.valueOf(book1.getPublishHouse());
        String publishHouse2 = String.valueOf(book2.getPublishHouse());
        return publishHouse1.compareTo(publishHouse2);
    }
}
