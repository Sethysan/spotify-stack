package com.techelevator.controller;

import com.techelevator.dao.JokeDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.DBJoke;
import com.techelevator.model.User;
import com.techelevator.service.DadJokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@PreAuthorize("isAuthenticates()")
public class JokeController {

    @Autowired
    private DadJokeService service;
    @Autowired
    private JokeDao jokeDao;
    @Autowired
    private UserDao userDao;

    @GetMapping("/joke")
    public String getJoke() {
        return service.getDadJoke(); // Fetches and returns a joke
    }

    @PostMapping("/joke")
    public void saveJoke(@RequestBody  DBJoke joke, Principal principal) {
        User user = userDao.getUserByUsername(principal.getName());
        jokeDao.saveJoke(joke.getJoke(), user.getId());


    }
}
