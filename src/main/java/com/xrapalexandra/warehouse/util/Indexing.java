package com.xrapalexandra.warehouse.util;

public class Indexing {

    private long idCount = 0;

    public long getId() {
        return idCount++;
    }
}
