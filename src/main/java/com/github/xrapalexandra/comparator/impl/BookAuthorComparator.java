package com.github.xrapalexandra.comparator.impl;

import com.github.xrapalexandra.comparator.BookComparator;
import com.github.xrapalexandra.model.Book;

public class BookAuthorComparator implements BookComparator {

    @Override
    public int compare(Book book1, Book book2) {
        return book1.getAuthor().compareTo(book2.getAuthor());
    }
}
