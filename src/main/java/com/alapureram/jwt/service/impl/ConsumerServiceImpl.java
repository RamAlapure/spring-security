package com.alapureram.jwt.service.impl;

import com.alapureram.jwt.exception.ResourceNotFoundException;
import com.alapureram.jwt.model.Consumer;
import com.alapureram.jwt.request.ConsumerRequest;
import com.alapureram.jwt.service.ConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final Map<String, Consumer> map = new HashMap<>();

    @PostConstruct
    public void init() {
        log.info("Configuring the service user");
        String id = "qwerty";
        String name = "service_user";
        Consumer consumer = getConsumer(id, name, "ROLE_ADMIN");
        map.put(id, consumer);
        log.info("Configured the service user");
    }

    private Consumer getConsumer(String id, String name, String role) {
        String secret = Base64.getEncoder().encodeToString((id + ":" + name).getBytes(StandardCharsets.UTF_8));
        return new Consumer(id, passwordEncoder.encode(secret), name, role);
    }

    @Override
    public Consumer find(String id) throws ResourceNotFoundException {
        Consumer consumer = map.get(id);
        if (consumer == null) throw new ResourceNotFoundException("Consumer not found for id: " + id);
        return consumer;
    }

    @Override
    public Consumer save(ConsumerRequest consumerRequest) {
        String id = UUID.randomUUID().toString();
        Consumer consumer = getConsumer(id, consumerRequest.getName(), "ROLE_USER");
        map.put(id, consumer);
        return consumer;
    }

}
