package com.github.xrapalexandra.comparator;


import com.github.xrapalexandra.comparator.impl.BookAuthorComparator;
import com.github.xrapalexandra.comparator.impl.BookIdComparator;
import com.github.xrapalexandra.comparator.impl.BookNameComparator;
import com.github.xrapalexandra.comparator.impl.BookPublishHouseComparator;
import com.github.xrapalexandra.comparator.impl.BookPriceComparator;
import com.github.xrapalexandra.comparator.impl.BookPublishingYearComparator;
import com.github.xrapalexandra.model.SortingType;

import java.util.HashMap;
import java.util.Map;

public class BookComparatorFactory {

    private static BookComparatorFactory instance;

    private final Map<SortingType, BookComparator> factory = new HashMap<>();

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

    public BookComparator create(SortingType[] sortingType) {
        BookComparator comparator = factory.get(sortingType[0]);
        for (int i = 1; i < sortingType.length; i++) {
            comparator.thenComparing(factory.get(sortingType[i]));
        }
        return comparator;
    }
}
