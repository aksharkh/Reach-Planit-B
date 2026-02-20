package com.example.Planit_Backend.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.Map;

@Slf4j
@Component
public class AppleTokenVerifier {

    @Value("${apple.clientId}")
    private String clientId;

    private static final String APPLE_AUTH_KEYS_URL = "https://appleid.apple.com/auth/keys";
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public AppleTokenPayload verify(String identityToken) {
        try {
            // Get Apple's public keys
            Map<String, Object> keys = getApplePublicKeys();

            // Decode token without verification first to get kid
    public AppleTokenPayload verify(String identityToken) {
        try {
            // Get Apple's public keys
            Map<String, Object> keys = getApplePublicKeys();

            // Decode token to get kid
            String kid = extractKeyId(identityToken);

            // Get the correct public key
            PublicKey publicKey = getPublicKeyFromAppleKeys(keys, kid);

            if (publicKey == null) {
                log.error("Could not find public key with kid: {}", kid);
                return null;
            }

            // Verify and parse the token
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(publicKey)
                    .build()
                    .parseClaimsJws(identityToken)
                    .getBody();

            // Validate claims
            if (!claims.getAudience().contains(clientId)) {
                log.error("Invalid audience in Apple token");
                return null;
            }

            // Extract user information
            String email = claims.get("email", String.class);
            String sub = claims.getSubject();

            return new AppleTokenPayload(email, sub);

        } catch (JwtException e) {
            log.error("Error verifying Apple token: {}", e.getMessage());
            return null;
        } catch (Exception e) {
            log.error("Unexpected error verifying Apple token: {}", e.getMessage());
            return null;
        }
    }
            String headerJson = new String(Base64.getUrlDecoder().decode(parts[0]));
            JsonNode header = objectMapper.readTree(headerJson);
            return header.get("kid").asText();
        } catch (Exception e) {
            log.error("Error extracting key id from token: {}", e.getMessage());
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getApplePublicKeys() {
        try {
            String response = restTemplate.getForObject(APPLE_AUTH_KEYS_URL, String.class);
    @SuppressWarnings("unchecked")
    private Map<String, Object> getApplePublicKeys() {
        try {
            String response = restTemplate.getForObject(APPLE_AUTH_KEYS_URL, String.class);
            return objectMapper.readValue(response, Map.class);
        } catch (Exception e) {
            log.error("Error fetching Apple public keys: {}", e.getMessage());
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    private PublicKey getPublicKeyFromAppleKeys(Map<String, Object> keys, String kid) {
        try {
            java.util.List<Map<String, Object>> keysList = (java.util.List<Map<String, Object>>) keys.get("keys");

            for (Map<String, Object> key : keysList) {
                if (kid.equals(key.get("kid"))) {
                    String n = (String) key.get("n"); // modulus
                    String e = (String) key.get("e"); // exponent

                    BigInteger modulus = new BigInteger(1, Base64.getUrlDecoder().decode(n));
                    BigInteger exponent = new BigInteger(1, Base64.getUrlDecoder().decode(e));

                    RSAPublicKeySpec spec = new RSAPublicKeySpec(modulus, exponent);
                    KeyFactory factory = KeyFactory.getInstance("RSA");
                    return factory.generatePublic(spec);
                }
            }
        } catch (Exception e) {
            log.error("Error creating public key from Apple keys: {}", e.getMessage());
        }
        return null;
    }   public final String sub;

        public AppleTokenPayload(String email, String sub) {
            this.email = email;
            this.sub = sub;
        }
    }
}
