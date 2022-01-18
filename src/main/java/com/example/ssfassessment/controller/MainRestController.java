/*
package com.example.ssfassessment.controller;

import com.example.ssfassessment.service.MainService;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="", produces = MediaType.APPLICATION_JSON_VALUE)
public class MainRestController {

    private MainService service;

    public MainRestController(MainService service) {
        this.service = service;
    }

    //use string if not posted from a form
    @PostMapping()
    public ResponseEntity<String> insertBoardGame(@RequestBody String body,
                                                  @ModelAttribute User user) {
     JsonArray jsonArray = service.save(body);
     JsonObject jsonObject = Json.createObjectBuilder()
             .add("insert count", jsonArray.size())
             .add("id", jsonArray)
             .build();

//        HttpHeaders headers = new HttpHeaders();
//        headers.add("X-AppName", "myapp");
//        new ResponseEntity(entity, headers, HttpStatus.OK);

//        ResponseEntity.status(HttpStatus.NOT_FOUND).body(entity).build();


     return ResponseEntity.status(HttpStatus.CREATED).body(jsonObject.toString());
    }
}
*/
