package ru.storeMVC.storeMVC.services;

import org.springframework.stereotype.Service;
import ru.storeMVC.storeMVC.model.Book;
<<<<<<< HEAD
=======
>>>>>>> 6976193 (new ordering)

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(Book book){
        bookRepository.addBook(book);
    }

    public List<Book> getBookAll(){
        return bookRepository.getBookAll();
    }

    public String update(Book book){
        return bookRepository.update(book);
    }

    public Book getBookById(int id){
        return bookRepository.getBookById(id);
    }

    public void deleteBookById(int id) {
        bookRepository.deleteBookById(id);
    }
}
