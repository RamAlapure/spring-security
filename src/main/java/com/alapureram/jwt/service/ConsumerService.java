package com.alapureram.jwt.service;

import com.alapureram.jwt.exception.ResourceNotFoundException;
import com.alapureram.jwt.model.Consumer;
import com.alapureram.jwt.request.ConsumerRequest;

public interface ConsumerService {

    Consumer find(String id) throws ResourceNotFoundException;

    Consumer save(ConsumerRequest consumerRequest);
}
