package com.github.xrapalexandra.comparator;

import com.github.xrapalexandra.model.Book;

import java.util.Comparator;

public interface BookComparator  extends Comparator<Book> {

    @Override
    int compare(Book book1, Book book2);
}
