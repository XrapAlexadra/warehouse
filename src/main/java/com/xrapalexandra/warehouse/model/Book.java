package com.xrapalexandra.warehouse.model;

import com.xrapalexandra.warehouse.util.Indexing;

import java.math.BigDecimal;

public class Book {

    private static final Indexing INDEXING = new Indexing();

    private long id;
    private String name;
    private String author;
    private PublishHouse publishHouse;
    private int publishingYear;
    private BigDecimal price;

    {
        id = INDEXING.getId();
    }

    public Book() {
    }

    public Book(String name, String authors, PublishHouse publishHouse, int publishingYear, BigDecimal price) {
        this.name = name;
        this.author = authors;
        this.publishHouse = publishHouse;
        this.publishingYear = publishingYear;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthors(String author) {
        this.author = author;
    }

    public PublishHouse getPublishHouse() {
        return publishHouse;
    }

    public void setPublishHouse(PublishHouse publishHouse) {
        this.publishHouse = publishHouse;
    }

    public int getPublishingYear() {
        return publishingYear;
    }

    public void setPublishingYear(int publishingYear) {
        this.publishingYear = publishingYear;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return publishingYear == book.publishingYear &&
                price.equals(book.price) &&
                name.equals(book.name) &&
                author.equals(book.author) &&
                publishHouse == book.publishHouse;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = publishingYear;
        result = prime * result + (price != null ? price.hashCode() : 0);
        result = prime * result + (name != null ? name.hashCode() : 0);
        result = prime * result + (author != null ? author.hashCode() : 0);
        result = prime * result + (publishHouse != null ? publishHouse.hashCode() : 0);

        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", author=").append(author);
        sb.append(", publishingHouse=").append(publishHouse);
        sb.append(", publishingYear=").append(publishingYear);
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }
}
