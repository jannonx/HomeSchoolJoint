package com.overdose.homeschooljoint.utils;

public class ServiceException extends Exception {

    /**
     * Constructs a {@code ServiceException} with no detail message.
     */
    public ServiceException() {
        super();
    }

    /**
     * Constructs a {@code ServiceException} with the specified
     * detail message.
     *
     * @param   s   the detail message.
     */
    public ServiceException(String s) {
        super(s);
    }
}
