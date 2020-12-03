package com.github.xrapalexandra.comparator.impl;

import com.github.xrapalexandra.comparator.BookComparator;
import com.github.xrapalexandra.model.Book;

public class BookPublishingYearComparator implements BookComparator {

    @Override
    public int compare(Book book1, Book book2) {
        return Integer.compare(book1.getPublishingYear(), book2.getPublishingYear());
    }
}
