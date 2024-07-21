/*
 * Copyright (c) 2024. MiguelProgrammer
 */

package br.com.techchallenge.fiap.neighborfood.config.exceptions;

public class Error {

    private String message;
    private Throwable throwable;

    public Error() {
    }

    public Error(String message, Throwable throwable) {
        this.message = message;
        this.throwable = throwable;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    @Override
    public String toString() {
        return "Error{" +
                "message='" + message + '\'' +
                ", throwable=" + throwable +
                '}';
    }
}
