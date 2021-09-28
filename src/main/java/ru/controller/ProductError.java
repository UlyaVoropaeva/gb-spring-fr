package ru.controller;

public class ProductError {
    private int statusCode;
    private String message;

    public ProductError() {

    }

    public ProductError(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
