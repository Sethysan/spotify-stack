package com.techelevator.controller;

import com.techelevator.service.SpotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import se.michaelthelin.spotify.model_objects.specification.Artist;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class SpotifyController {

    @Autowired
    private SpotifyService spotifyService;

    @GetMapping("/artist/{name}")
    public Artist getArtist(@PathVariable String name){
        return spotifyService.getArtist(name);
    }
}
