package com.github.xrapalexandra.exeptoin;

public class DaoException extends Exception{

    public DaoException() {
        super();
    }

    public DaoException(String s) {
        super(s);
    }

    public DaoException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public DaoException(Throwable throwable) {
        super(throwable);
    }
}
