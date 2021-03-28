package com.sml.kafkareceiver;

import com.sml.kafkareceiver.config.ConsumerKafkaProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {ConsumerKafkaProperties.class})
public class KafkaReceiverApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaReceiverApplication.class, args);
    }

}
