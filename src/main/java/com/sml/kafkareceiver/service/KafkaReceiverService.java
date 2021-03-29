package com.sml.kafkareceiver.service;

import com.sml.kafkareceiver.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nlmk.l3.sup.IntegralParameters;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaReceiverService {

    @KafkaListener(topics = {"${kafka.topic}"})
    public void receiveMessage(@Payload IntegralParameters integralParameters,
                               @Headers MessageHeaders headers) {
        log.info("--- received integralParameters: {}", integralParameters);
        headers.keySet().forEach(key -> {
            log.info("--- {}: {}", key, headers.get(key));
        });
    }
}