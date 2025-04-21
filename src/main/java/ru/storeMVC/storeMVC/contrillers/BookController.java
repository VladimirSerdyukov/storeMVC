package ru.storeMVC.storeMVC.contrillers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.storeMVC.storeMVC.model.Book;
import ru.storeMVC.storeMVC.repository.BookRepository;
import ru.storeMVC.storeMVC.services.BookService;

@RestController
@RequestMapping("/book")
public class BookController {

    public final BookService book;
    public final ObjectMapper objectMapper;

    public BookController(BookService bookService, ObjectMapper objectMapper) {
        this.book = bookService;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public ResponseEntity<String> getBookAll() throws JsonProcessingException {
        return ResponseEntity.ok(objectMapper.writeValueAsString(book.getBookAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getBookById(@PathVariable int id) throws JsonProcessingException {
        return ResponseEntity.ok(objectMapper.writeValueAsString(book.getBookById(id)));
    }

    @PostMapping
    public ResponseEntity<String> addBook(@RequestBody Book book){
        this.book.addBook(book);
        return ResponseEntity.ok("ok");
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody Book book) throws JsonProcessingException {
        return ResponseEntity.ok(this.book.update(book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        book.deleteBookById(id);
        return ResponseEntity.ok("Сущность удалена");
    }

}
