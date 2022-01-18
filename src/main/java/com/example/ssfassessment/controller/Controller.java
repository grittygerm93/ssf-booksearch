package com.example.ssfassessment.controller;/*
package com.example.ssfassessment.controller;

import com.example.ssfassessment.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BookController {

    private BookService service;

    public BookController(BookService bookService) {
        this.service = bookService;
    }

    //only use multivaluemap if posted from a form
    @PostMapping(path= "/{id}" ,params = "action=update")
    public String convert(Model model,
                          @RequestBody MultiValueMap<String, String> form,
                          @RequestParam(name="units", defaultValue="metrics") String units,
                          @RequestParam MultiValueMap<String, String> queryParams,
                          @PathVariable(name="city", required=true) String city) {

        model.addAttribute("person", new Contacts());
        return "";
    }

    //redirecting and accepting file uploads
    @PostMapping("/sendfile")
    public String postItem(Model model, @RequestParam MultipartFile file, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message",
                "%s was uploaded".formatted(file.getOriginalFilename()));

        return "redirect:/";
    }


}
*/
