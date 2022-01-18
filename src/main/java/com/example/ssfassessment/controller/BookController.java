package com.example.ssfassessment.controller;

import com.example.ssfassessment.model.Book;
import com.example.ssfassessment.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book/works")
public class BookController {

    private Logger logger = LoggerFactory.getLogger("BookController.class");
    private BookService service;

    public BookController(BookService bookService) {
        this.service = bookService;
    }

    //only use multivaluemap if posted from a form
    @GetMapping("/{id}")
    public String getBookWorks(Model model, @PathVariable(name="id") String works) {
        logger.info("{}","works/%s".formatted(works));

        Book book = service.searchWorks("/works/%s".formatted(works));

        model.addAttribute("item", book);
        return "details";
    }
}
