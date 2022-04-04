package com.alapureram.jwt.repository;

import com.alapureram.jwt.model.TokenDetails;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class TokenRepository {

    Map<String, TokenDetails> map = new HashMap<>();

    public TokenDetails get(String key) {
        return map.getOrDefault(key, null);
    }

    public TokenDetails put(String key, TokenDetails token) {
        return map.put(key, token);
    }
}
