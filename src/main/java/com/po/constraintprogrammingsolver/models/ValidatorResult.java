package com.po.constraintprogrammingsolver.models;

/**
 * Created by Aleksander on 2015-01-03.
 */
public class ValidatorResult {
    private final boolean error;
    private final String message;

    public ValidatorResult(boolean error, String message) {
        this.error = error;
        this.message = message;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
