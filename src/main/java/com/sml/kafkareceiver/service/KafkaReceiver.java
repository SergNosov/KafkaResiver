package com.sml.kafkareceiver.service;

import com.sml.kafkareceiver.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaReceiver {

    @KafkaListener(topics = {"${kafka.topic}"}
    )
    public void receiveMessage(@Payload User user,
                               @Headers MessageHeaders headers) {
        log.info("--- received user: {}", user);
        headers.keySet().forEach(key -> {
            log.info("--- {}: {}", key, headers.get(key));
        });
    }
}
