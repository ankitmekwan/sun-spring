package com.computers.sun.exception;

public class SpringException extends Exception {

    String additionalDetails;

    public SpringException(final String msg) {
        super(msg);
    }

    public SpringException(final Exception e) {
        super(e);
    }

    public SpringException(final String additionalDetails, final Exception e) {
        super(additionalDetails, e);
        this.additionalDetails = additionalDetails;
    }

    @Override
    public String getLocalizedMessage() {
        return additionalDetails;
    }
}
