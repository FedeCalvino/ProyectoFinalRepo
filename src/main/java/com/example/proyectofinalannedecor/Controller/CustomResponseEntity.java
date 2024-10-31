package com.example.proyectofinalannedecor.Controller;

import org.springframework.http.HttpStatus;

public class CustomResponseEntity<T> {

    private HttpStatus status;
    private String message;
    private T body;
    public CustomResponseEntity(){

    }
    public CustomResponseEntity(HttpStatus status, String message, T body) {
        this.status = status;
        this.message = message;
        this.body = body;
    }

    public CustomResponseEntity(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public static <T> CustomResponseEntity<T> ok(String message, T body) {
        return new CustomResponseEntity<>(HttpStatus.OK, message, body);
    }

    public static <T> CustomResponseEntity<T> ok(String message) {
        return new CustomResponseEntity<>(HttpStatus.OK, message);
    }

    public static <T> CustomResponseEntity<T> error(String message, HttpStatus status) {
        return new CustomResponseEntity<>(status, message);
    }

    public static <T> CustomResponseEntity<T> badRequest(String message) {
        return new CustomResponseEntity<>(HttpStatus.BAD_REQUEST, message);
    }

    public static <T> CustomResponseEntity<T> error(String message) {
        return new CustomResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }
}
