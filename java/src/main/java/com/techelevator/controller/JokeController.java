package com.techelevator.controller;

import com.techelevator.service.DadJokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class JokeController {
    @Autowired
    private DadJokeService service;

    @GetMapping("/joke")
    public String getJoke() {
        return service.getDadJoke();
    }

}
