package com.github.xrapalexandra.comparator.impl;

import com.github.xrapalexandra.comparator.BookComparator;
import com.github.xrapalexandra.model.Book;

import java.math.BigDecimal;

public class BookPriceComparator implements BookComparator {

    @Override
    public int compare(Book book1, Book book2) {
        return book1.getPrice().compareTo(book2.getPrice());
    }
}
