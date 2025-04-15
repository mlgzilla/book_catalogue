package com.example.book_catalogue.exception.handler.model;

public record ErrorModel(
        Integer status,
        String message
) {
}
