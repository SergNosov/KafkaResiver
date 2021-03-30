package com.sml.kafkareceiver.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties("kafka")
public class ConsumerKafkaProperties {

    private final String kafkaServer;
    private final String kafkaGroupId;
    private final String topicIP;
    private final String topicUP;

    public ConsumerKafkaProperties(@Value("${kafka.bootstrap-servers}") String kafkaServer,
                                   @Value("${kafka.consumer.group-id}") String kafkaGroupId,
                                   @Value("${kafka.topicIP}") String topicIP,
                                   @Value("${kafka.topicUP}")String topicUP) {
        this.kafkaServer = kafkaServer;
        this.kafkaGroupId = kafkaGroupId;
        this.topicIP = topicIP;
        this.topicUP = topicUP;
    }
}
