package com.github.xrapalexandra.comparator.impl;

import com.github.xrapalexandra.comparator.BookComparator;
import com.github.xrapalexandra.model.Book;

public class BookIdComparator implements BookComparator {

    @Override
    public int compare(Book book1, Book book2) {
        return Long.compare(book1.getId(), book2.getId());
    }
}
