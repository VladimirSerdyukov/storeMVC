package ru.storeMVC.storeMVC.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.storeMVC.storeMVC.model.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookRepositoryImpl implements BookRepository {

    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate parameterJdbcTemplate;

    public BookRepositoryImpl(JdbcTemplate template, NamedParameterJdbcTemplate parameterJdbcTemplate) {
        this.template = template;
        this.parameterJdbcTemplate = parameterJdbcTemplate;
    }

    @Override
    public void addBook(Book book) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(template)
                .withTableName("book")
                .usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", book.getTitle());
        parameters.put("author", book.getAuthor());
        parameters.put("publication_year", book.getPublicationYear());
        simpleJdbcInsert.execute(parameters);
    }

    @Override
    public List<Book> getBookAll(){
        return template.query("SELECT * FROM book", new BookRowMapper());
    }

    @Override
    public String update(Book book) {
        template.update("UPDATE book Set title = ?, author = ?, publication_year=? where id = ?", book.getTitle(), book.getAuthor(), book.getPublicationYear(), book.getId());
        String sql = "select title from book where id= :id";
        SqlParameterSource namedParameter = new MapSqlParameterSource().addValue("id", book.getId());
        String title = parameterJdbcTemplate.queryForObject(sql, namedParameter, String.class);
        return title;
    }

    @Override
    public Book getBookById(int id) {
        String sql = "select * from book where id = :id";
        SqlParameterSource namedParametr = new MapSqlParameterSource().addValue("id", id);

        return parameterJdbcTemplate.queryForObject(sql, namedParametr, new RowMapper<Book>()  {
            @Override
            public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPublicationYear(rs.getInt("publication_year"));
                return book;
            }
        });
    }

    @Override
    public void deleteBookById(int id) {
        template.update("DELETE FROM book Where id = ?", id);
    }
}
