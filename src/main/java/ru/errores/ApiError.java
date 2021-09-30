package ru.errores;

public class ApiError {
    private int statusCode;
    private String message;
    private  String  details;

    public ApiError() {

    }
    public ApiError(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;

    }


    public ApiError(int statusCode, String message,String details) {
        this.statusCode = statusCode;
        this.message = message;
        this.details = details;
    }
}
