package com.github.xrapalexandra.model;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {

    private static Warehouse instance;

    private List<Book> books = new ArrayList<>();

    private Warehouse() {
    }

    public static Warehouse getInstance() {
        if (instance == null) {
            instance = new Warehouse();
        }
        return instance;
    }

    public void add(Book book) {
        books.add(book);
    }

    public void set(int index, Book book) {
        books.set(index, book);
    }

    public Book get(int index) {
        return books.get(index);
    }

    public int indexOf(Book book) {
        return books.indexOf(book);
    }

    public int size() {
        return books.size();
    }

    public void remove(int index) {
        books.remove(index);
    }

    public void remove(Book book) {
        books.remove(book);
    }

    public boolean contains(Book book) {
        return books.contains(book);
    }

    public void clear() {
        books.clear();
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "books=" + books +
                '}';
    }
}
