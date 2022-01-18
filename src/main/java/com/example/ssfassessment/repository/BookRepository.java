package com.example.ssfassessment.repository;

import com.example.ssfassessment.model.Book;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Repository
public class BookRepository {

    private final Logger logger = LoggerFactory.getLogger("BookRepository.class");
    private final RedisTemplate<String, String> template;

    public BookRepository(RedisTemplate<String, String> template) {
        this.template = template;
    }

    public String save(String id, Book book) {
        logger.info("the saved json obj is {}", book.toJson().toString());
        template.opsForValue().set(id, book.toJson().toString(), 10L, TimeUnit.MINUTES);
        return id;
    }

    public Optional<Book> get(String id) {
        String store = template.opsForValue().get(id);
        if (store == null) {
            return Optional.empty();
        }
        try (InputStream is = new ByteArrayInputStream(store.getBytes())) {
            JsonReader reader = Json.createReader(is);
            JsonObject data = reader.readObject();
            return Optional.of(Book.createWithDetails(data));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

//    public String update(String id, String body) {
//        template.opsForValue().set(id, body, 10L, TimeUnit.MINUTES);
//        return id;
//    }

    private String normalize(String k) {
        return k.trim().toLowerCase();
    }


}
