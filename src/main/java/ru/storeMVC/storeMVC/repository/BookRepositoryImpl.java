package ru.storeMVC.storeMVC.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.storeMVC.storeMVC.model.Book;

public class BookRepositoryImpl implements BookRepository{

    private final JdbcTemplate template;

    public BookRepositoryImpl(JdbcTemplate template) {
        this.template = template;
    }

    public void addBook(Book book) {
        template.update("insert into book values (?,?,?)", book.getTitle(), book.getAuthor(), book.getPublicationYear());
    }
}
