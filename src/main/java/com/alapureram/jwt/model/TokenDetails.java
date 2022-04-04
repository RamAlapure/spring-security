package com.alapureram.jwt.model;

public class TokenDetails {

    private String id;
    private String token;
    private long expiry;

    public TokenDetails(String id, String token, long expiry) {
        this.id = id;
        this.token = token;
        this.expiry = expiry;
    }

    public String getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public long getExpiry() {
        return expiry;
    }

}
