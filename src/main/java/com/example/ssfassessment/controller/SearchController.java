package com.example.ssfassessment.controller;

import com.example.ssfassessment.model.Book;
import com.example.ssfassessment.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class SearchController {

    private BookService service;
    private String previousTitle;

    public SearchController(BookService bookService) {
        this.service = bookService;
    }


    @PostMapping("/main")
    public String convert(Model model, @RequestBody MultiValueMap<String, String> form) {
        //to support the back function
        previousTitle = form.getFirst("title");

        List<Book> list = service.search(form.getFirst("title"));

        model.addAttribute("title", form.getFirst("title"));

        if(list.isEmpty()) {
            model.addAttribute("errormsg", "no such books were found");
        }

        model.addAttribute("list", list);
        return "linkspage";
    }

    @GetMapping("/main")
    public String convert1(Model model) {
        //to support the back function

        List<Book> list = service.search(previousTitle);
        model.addAttribute("title", previousTitle);

        if(list.isEmpty()) {
            model.addAttribute("errormsg", "no such books were found");
        }

        model.addAttribute("list", list);
        return "linkspage";
    }

    //only use multivaluemap if posted from a form
   /* @PostMapping(path= "/{id}" ,params = "action=update")
    public String convert(Model model,
                          @RequestBody MultiValueMap<String, String> form,
                          @RequestParam(name="units", defaultValue="metrics") String units,
                          @RequestParam MultiValueMap<String, String> queryParams,
                          @PathVariable(name="city", required=true) String city) {

        model.addAttribute("person", new Contacts());
        return "";
    }*/

    //redirecting and accepting file uploads
/*    @PostMapping("/sendfile")
    public String postItem(Model model, @RequestParam MultipartFile file, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message",
                "%s was uploaded".formatted(file.getOriginalFilename()));

        return "redirect:/";
    }*/


}
