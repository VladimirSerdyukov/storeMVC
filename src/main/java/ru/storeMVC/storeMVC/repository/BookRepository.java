package ru.storeMVC.storeMVC.repository;

import ru.storeMVC.storeMVC.model.Book;

import java.util.List;

public interface BookRepository {

    void addBook(Book book);

    List<Book> getBookAll();

    String update(Book book);

    Book getBookById(int id);

    void deleteBookById(int id);
}
