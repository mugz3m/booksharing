package ru.george.booksharing.server.controller.dto;

public record GetBookResponse(Integer id, String title, String author, Integer year, String genre, Integer userId) {
}