package com.example.ssfassessment.service;

import com.example.ssfassessment.model.Book;
import com.example.ssfassessment.repository.BookRepository;
import jakarta.json.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookService {

    private BookRepository repo;
    private RestTemplate restTemplate;
    private Logger logger = LoggerFactory.getLogger(BookService.class);

    @Value("${book.link1}")
    private String url1;

    @Value("${book.link2}")
    private String url2;

    public BookService(BookRepository repo, RestTemplate restTemplate) {
        this.repo = repo;
        this.restTemplate = restTemplate;
    }

    public JsonArray save(String body) {
//        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        try (InputStream is = new ByteArrayInputStream(body.getBytes())) {
            JsonReader reader = Json.createReader(is);
            JsonArray data = reader.readArray();

            /*data.stream()
                    .map(e -> e.asJsonObject().getString("game"))
                    .forEach(e -> arrayBuilder.add(repo.save(e)));*/

        } catch (IOException e) {
            e.printStackTrace();
        }
//        return arrayBuilder.build();
        return null;
    }

    public Optional<JsonObject> get(String id) {
//        String item = repo.get(id);
//        if (item == null) {
//            return Optional.empty();
//        }
 /*       JsonObject jsonObject = Json.createObjectBuilder()
                .add("item", item)
                .build();*/
//        return Optional.ofNullable(jsonObject);
        return null;
    }

    public JsonObject update(String id, String body) {

        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();

        try (InputStream is = new ByteArrayInputStream(body.getBytes())) {
            JsonReader reader = Json.createReader(is);
            JsonObject data = reader.readObject();

            /*String gameVal = data.getString("game");

            jsonObjectBuilder.add("update count", 1)
                    .add("id", repo.update(id, gameVal));*/

        } catch (IOException e) {
            e.printStackTrace();
        }
//        return jsonObjectBuilder.build();
        return null;
    }

    public List<Book> search(String title) {
//        http://openlibrary.org/search.json?q=the+lord+of+the+rings
        String url1New = UriComponentsBuilder
                .fromUriString(url1)
                .queryParam("title", title.trim().replace(" ", "+"))
                .queryParam("limit","20")
                .toUriString();

        RequestEntity req = RequestEntity.get(url1New).accept(MediaType.APPLICATION_JSON).build();
        ResponseEntity<String> resp = restTemplate.exchange(req, String.class);

        try (InputStream is = new ByteArrayInputStream(resp.getBody().getBytes())) {
            JsonReader reader = Json.createReader(is);
            JsonObject data = reader.readObject();

            JsonArray books = data.getJsonArray("docs");
            List<Book> BookList = books.stream()
                    .map(e -> (JsonObject) e)
                    .map(Book::create)
                    .collect(Collectors.toList());
            logger.info("the size of the list is {}", BookList.size());
//            repository.save(country, weatherList);
            return BookList;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;

    }

    public Book searchWorks(String works) {

        Optional<Book> bookCache = repo.get(works);
        if(bookCache.isPresent()) {
            logger.info("cache hit!");
            return bookCache.get();
        }
        String url2New = String.format("%s%s.json", url2, works);

        logger.info("uri is {}", url2New);

        RequestEntity req = RequestEntity.get(url2New).accept(MediaType.APPLICATION_JSON).build();
        ResponseEntity<String> resp = restTemplate.exchange(req, String.class);

        try (InputStream is = new ByteArrayInputStream(resp.getBody().getBytes())) {
            JsonReader reader = Json.createReader(is);
            JsonObject data = reader.readObject();
            String title = data.getString("title");

            String description = "";
            String excerpt = "";
            String icon = "";

            for (String item:data.keySet()) {
                if(item.equals("description")) {
                    for (Map.Entry<String, JsonValue> unit:data.entrySet()) {
                        if(unit.getValue().getValueType() == JsonValue.ValueType.OBJECT && unit.getKey().equals("description")) {
                            description = data.getJsonObject("description").getString("value");
                            break;
                        }
                        if(unit.getKey().equals("description")) {
                            description = data.getString("description"); //may not be present
                        }
                    }
//                    data.values().forEach(e -> System.out.println(e.getValueType()));
                }
                if(item.equals("excerpts")) {
                    excerpt = data.getJsonArray("excerpts").get(0).asJsonObject().getString("excerpt"); //may not be present
                }
                if(item.equals("covers")) {
                    icon = data.getJsonArray("covers").getJsonNumber(0).toString(); //may not be present
                }
            }

//            Book(String title, String description, String excerpt, String icon)
//            https://covers.openlibrary.org/b/id/10222599-M.jpg
            Book book = new Book(title, description, excerpt,
                    "https://covers.openlibrary.org/b/id/%s-M.jpg".formatted(icon));

            logger.info("the content of the book is {}", book);
            String id = repo.save(works, book);
            logger.info("the saved id is {}", id);
            return book;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return new Book();

    }

//    resttemplate boilerplate
/*    public List<Weather> getInfoFromApi(String country) {
        Optional<List<Weather>> optList = repository.getFromCache(country);
        if(optList.isPresent()) {
            return optList.get();
        }

        String url2 = UriComponentsBuilder
                .fromUriString(url1)
                .queryParam("q", city.trim().replace(" ", "+"))
                .queryParam("appid", apiKey)
                .queryParam("mode", "metric")
                .toUriString();

        RequestEntity req = RequestEntity.get(url2).accept(MediaType.APPLICATION_JSON).build();

        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add(“name”, “fred”);
        form.add(“email”, “fred@gmail.com”);
        RequestEntity<MultiValueMap<String, String>> req = RequestEntity
        .post(url)
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .headers(“Accept”, MediaType.APPLICATION_JSON)
        .body(form, MultiValueMap<String, String>.class)

        ResponseEntity<String> resp = restTemplate.exchange(req, String.class);


        try (InputStream is = new ByteArrayInputStream(resp.getBody().getBytes())) {
            JsonReader reader = Json.createReader(is);
            JsonObject data = reader.readObject();

            JsonArray weather = data.getJsonArray("weather");
            String cityName = data.getString("name");
            double temp = data.getJsonObject("main").getJsonNumber("temp").doubleValue();
            List<Weather> weatherList = weather.stream()
                    .map(e -> (JsonObject)e)
                    .map(Weather::create)
                    .map(e -> {
                        e.setCityName(cityName);
                        e.setTemp(temp);
                        return e;
                    })
                    .collect(Collectors.toList());
            repository.save(country, weatherList);
            return weatherList;

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }*/


}
