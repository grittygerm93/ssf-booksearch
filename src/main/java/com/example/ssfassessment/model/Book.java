package com.example.ssfassessment.model;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Book {
    private String title;
    private String key;
    private String description;
    private String excerpt;
    private String icon;
    private boolean wasCached;

    public Book() {
    }

    public Book(String title, String key) {
        this.title = title;
        this.key = key;
    }

    public Book(String title, String description, String excerpt, String icon) {
        this.title = title;
        this.description = description;
        this.excerpt = excerpt;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isWasCached() {
        return wasCached;
    }

    public void setWasCached(boolean wasCached) {
        this.wasCached = wasCached;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("title", title)
                .add("description", description)
                .add("icon", icon)
                .add("excerpt", excerpt)
                .build();
    }

    public static Book create(JsonObject o) {
        Book book = new Book();
        book.setKey(o.getString("key"));
        book.setTitle(o.getString("title"));
//        book.setIcon(String.format("http://openweathermap.org/img/wn/%s@2x.png", o.getString("icon")));
//        System.out.println(String.format("http://openweathermap.org/img/wn/%s@2x.png", o.getString("icon")));
        return book;
    }

    public static Book createWithDetails(JsonObject o) {
        Book book = new Book();
        book.setTitle(o.getString("title"));
        book.setDescription(o.getString("description"));
        book.setExcerpt(o.getString("excerpt"));
        book.setIcon(o.getString("icon"));
        book.setWasCached(true);
//        book.setIcon(String.format("http://openweathermap.org/img/wn/%s@2x.png", o.getString("icon")));
//        System.out.println(String.format("http://openweathermap.org/img/wn/%s@2x.png", o.getString("icon")));
        return book;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", excerpt='" + excerpt + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
