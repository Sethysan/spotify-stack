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


    SpotifyApi spotifyApi;

    private ClientCredentialsRequest CLIENT_CREDENTIAL_REQUEST;

    public SpotifyService(@Value("${spotify.client.id}") String id, @Value("${spotify.client.secret}") String secret) {
        spotifyApi = new SpotifyApi.Builder()
                .setClientId(id)
                .setClientSecret(secret)
                .setRedirectUri(URI.create("http://localhost:9000"))
                .build();
        CLIENT_CREDENTIAL_REQUEST =
                spotifyApi.clientCredentials().build();
        getClientCredentials();
    }

    public Artist getArtist(String artistName) {
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

    private void getClientCredentials() {
        try {
            ClientCredentials clientCredentials =
                    CLIENT_CREDENTIAL_REQUEST.execute();
            spotifyApi.setAccessToken(clientCredentials.getAccessToken());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
