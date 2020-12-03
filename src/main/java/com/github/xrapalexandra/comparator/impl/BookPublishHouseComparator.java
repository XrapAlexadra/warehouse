package com.github.xrapalexandra.comparator.impl;

import com.github.xrapalexandra.comparator.BookComparator;
import com.github.xrapalexandra.model.Book;

public class BookPublishHouseComparator implements BookComparator {

    @Override
    public int compare(Book book1, Book book2) {
        String publishHouse1 = String.valueOf(book1.getPublishHouse());
        String publishHouse2 = String.valueOf(book2.getPublishHouse());
        return publishHouse1.compareTo(publishHouse2);
    }
}
