package com.clip.core.service;

import com.clip.core.config.TiktokConfig;
import com.clip.core.config.TwitchConfig;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;

@Service
public class ExternalApiService {

    private String accessTokenTwitch;
    private String accessTokenTiktok;
    private String tokenExpirationTwitch;
    private String tokenExpirationTiktok;

    @Autowired
    private TwitchConfig twitchConfig;

    @Autowired
    private TiktokConfig tiktokConfig;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public HttpResponse<String> getClips() {
        if (accessTokenTwitch == null || isTokenExpired(tokenExpirationTwitch)) {
            authenticateTwitch();
        }

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(twitchConfig.getUrlClips() + "?broadcaster_id=" + twitchConfig.getBroadcasterId() + "&first=100"))
                    .header("Authorization", "Bearer " + accessTokenTwitch)
                    .header("Client-ID", twitchConfig.getClientId())
                    .build();

            HttpResponse<String> response = client.send(request,
            HttpResponse.BodyHandlers.ofString());

            return response;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void authenticateTwitch() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            String body = "grant_type=" + twitchConfig.getGrantType() + "&client_id=" + twitchConfig.getClientId()
                    + "&client_secret=" + twitchConfig.getSecretId();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(twitchConfig.getUrlAuth()))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonNode json = objectMapper.readTree(response.body());
            System.out.println("Response: " + json);
            accessTokenTwitch = json.get("access_token").asText();
            int expiresIn = json.get("expires_in").asInt();
            tokenExpirationTwitch = Instant.now().plusSeconds(expiresIn).toString();

            System.out.println("Authenticated Twitch: " + accessTokenTwitch);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void authenticateTiktok() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            String body = "grant_type=" + tiktokConfig.getGrantType() + "&client_key=" + tiktokConfig.getClientId()
                    + "&client_secret=" + tiktokConfig.getSecretId();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(tiktokConfig.getUrlAuth()))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonNode json = objectMapper.readTree(response.body());
            accessTokenTiktok = json.get("access_token").asText();
            int expiresIn = json.get("expires_in").asInt();
            tokenExpirationTiktok = Instant.now().plusSeconds(expiresIn).toString();

            System.out.println("Authenticated Tiktok: " + accessTokenTiktok);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isTokenExpired(String tokenExpiration) {
        return tokenExpiration == null || Instant.now().isAfter(Instant.parse(tokenExpiration).minusSeconds(30));
    }
}
