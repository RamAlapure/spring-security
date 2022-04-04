package com.alapureram.jwt.service.impl;

import com.alapureram.jwt.model.Consumer;
import com.alapureram.jwt.model.TokenDetails;
import com.alapureram.jwt.repository.TokenRepository;
import com.alapureram.jwt.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public TokenDetails create(Consumer consumer) {
        String token = UUID.randomUUID().toString();
        TokenDetails tokenDetails = new TokenDetails(consumer.getUsername(), token, System.currentTimeMillis());
        tokenRepository.put(token, tokenDetails); // for future validation
        return tokenDetails;
    }

    @Override
    public TokenDetails find(String token) {
        return tokenRepository.get(token);
    }
}
