package com.xrapalexandra.warehouse.comparator.factory;

import com.xrapalexandra.warehouse.comparator.BookAuthorComparator;
import com.xrapalexandra.warehouse.comparator.BookIdComparator;
import com.xrapalexandra.warehouse.comparator.BookNameComparator;
import com.xrapalexandra.warehouse.comparator.BookPublishHouseComparator;
import com.xrapalexandra.warehouse.comparator.BookPriceComparator;
import com.xrapalexandra.warehouse.comparator.BookPublishingYearComparator;
import com.xrapalexandra.warehouse.model.Book;
import com.xrapalexandra.warehouse.model.SortingType;

import java.util.Comparator;
import java.util.EnumMap;
import java.util.Map;

public class BookComparatorFactory {

    private static BookComparatorFactory instance;

    private final Map<SortingType, Comparator<Book>> factory = new EnumMap<>(SortingType.class);

    {
        factory.put(SortingType.ID, new BookIdComparator());
        factory.put(SortingType.NAME, new BookNameComparator());
        factory.put(SortingType.AUTHOR, new BookAuthorComparator());
        factory.put(SortingType.PUBLISH_HOUSE, new BookPublishHouseComparator());
        factory.put(SortingType.PUBLISH_YEAR, new BookPublishingYearComparator());
        factory.put(SortingType.PRICE, new BookPriceComparator());
    }

    private BookComparatorFactory() {
    }

    public static BookComparatorFactory getInstance() {
        if (instance == null) {
            instance = new BookComparatorFactory();
        }
        return instance;
    }

    public Comparator<Book> build(SortingType[] sortingType) {
        Comparator<Book> comparator = factory.get(sortingType[0]);
        for (int i = 1; i < sortingType.length; i++) {
            comparator.thenComparing(factory.get(sortingType[i]));
        }
        return comparator;
    }
}
