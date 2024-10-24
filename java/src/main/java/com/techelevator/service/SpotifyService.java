package com.techelevator.service;

import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchArtistsRequest;

import java.io.IOException;
import java.net.URI;
@Service
public class SpotifyService {

//     private final String API_URL = "https://api.spotify.com/v1/search?q=artist:";
//    @Value("${CLIENT_ID")
    private String CLIENT_ID = "f9d1b473e5b24adb9454bdb1fc6fb59e";
//    @Value("${CLIENT_SECRET")
    private String CLIENT_SECRET = "7d2bd5c6335c455aa232fb34b5e44d92";

    SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(CLIENT_ID)
            .setClientSecret(CLIENT_SECRET)
            .setRedirectUri(URI.create("http://localhost:9000"))
            .build();

    private final ClientCredentialsRequest CLIENT_CREDENTIAL_REQUEST =
       spotifyApi.clientCredentials().build();
    public SpotifyService() {
        getClientCredentials();
    }

    public Artist getArtist(String artistName){
        System.out.println(artistName);
        SearchArtistsRequest searchArtistsRequest =
                spotifyApi.searchArtists(artistName).build();
        try {
            final Paging<Artist> artistPaging = searchArtistsRequest.execute();

            return artistPaging.getItems()[0];
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    private void getClientCredentials(){
        try {
            ClientCredentials clientCredentials =
                    CLIENT_CREDENTIAL_REQUEST.execute();
            spotifyApi.setAccessToken(clientCredentials.getAccessToken());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
