package com.techelevator.controller;

import com.techelevator.dao.ArtistDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.User;
import com.techelevator.service.SpotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.michaelthelin.spotify.model_objects.specification.Artist;

import java.security.Principal;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class SpotifyController {

    @Autowired
    private SpotifyService spotifyService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ArtistDao artistDao;

    @GetMapping("/artist/{name}")
    public Artist getArtist(@PathVariable String name){
        return spotifyService.getArtist(name);
    }

    @PostMapping ("artist")
    public void saveArtist(@RequestBody Artist artist, Principal principal){
        User user = userDao.getUserByUsername(principal.getName());
        artistDao.saveArtist(artist, user.getId());

    }
}
