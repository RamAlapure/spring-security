package com.alapureram.jwt.service;

import com.alapureram.jwt.model.Consumer;
import com.alapureram.jwt.model.TokenDetails;

public interface TokenService {

    TokenDetails create(Consumer consumer);

    TokenDetails find(String token);
}
