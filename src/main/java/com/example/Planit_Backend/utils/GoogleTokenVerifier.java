package com.example.Planit_Backend.utils;


import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class GoogleTokenVerifier {


    @Value("${google.clientId}")
    private String clientId;

    public GoogleIdToken.Payload verify(String idTokenString) {
        try {

            var transport = GoogleNetHttpTransport.newTrustedTransport();
            var jsonFactory = GsonFactory.getDefaultInstance();

            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                    .setAudience(Collections.singletonList(clientId))
                    .build();

            GoogleIdToken idToken = verifier.verify(idTokenString);

            if(idToken != null) {
                return idToken.getPayload();
            } else {
                return null;
            }

        } catch (Exception e) {
            return null;
        }
    }
}
