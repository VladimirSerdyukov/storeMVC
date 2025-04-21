package ru.storeMVC.storeMVC;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.storeMVC.storeMVC.contrillers.BookController;
import ru.storeMVC.storeMVC.model.Book;
import ru.storeMVC.storeMVC.repository.BookRepository;
import ru.storeMVC.storeMVC.repository.BookRepositoryImpl;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
class StoreMvcApplicationTests {
    @Autowired
    private ApplicationContext StoreMvcApplication;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private BookRepository bookRepository;
//    public StoreMvcApplicationTests(ApplicationContext storeMvcApplication, MockMvc mockMvc, ObjectMapper objectMapper, BookRepository bookRepository) {
//        StoreMvcApplication = storeMvcApplication;
//        this.mockMvc = mockMvc;
//        this.objectMapper = objectMapper;
//        this.bookRepository = bookRepository;
//    }

    @Test
    void contextLoads() {
    }

    @Test
    public void testBookControllerGetAllBook() throws Exception {
        RequestBuilder requestBuilder = get("/book");
        this.mockMvc.perform(requestBuilder)
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.valueOf("text/plain;charset=UTF-8")),
                        content().json("""
                                                                        [
                                                                    {
                                "id": 1,"title":"War and Peace","author":"Lev Nikolaevich Tolstoy","publicationYear": 1896
                                                                    }]
                                """)
                );
    }

    @Test
    public void postBook() throws Exception {
        Book book = new Book();
        book.setTitle("mockTitle");
        book.setAuthor("mockAuthor");
        book.setPublicationYear(1110);

        RequestBuilder requestBuilder = post("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book));

        this.mockMvc.perform(requestBuilder)
                .andExpectAll(status().isOk(),
                        content().contentType(MediaType.valueOf("text/plain;charset=UTF-8")),
                        content().string("ok"));

        List<Book> books = bookRepository.getBookAll();
        for (Book i : books) {
            if (i.getId() == 1)
            {}
            else {
                this.bookRepository.deleteBookById(i.getId());
            }
        }
    }
}
