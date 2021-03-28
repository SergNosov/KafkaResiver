package com.sml.kafkareceiver.config;

import com.sml.kafkareceiver.model.User;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class UserConsumerConfig {

    private final ConsumerKafkaProperties consumerProperties;

    @Bean
    public Map<String,Object> consumerConfigs(){
        Map<String,Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, consumerProperties.getKafkaServer());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG,consumerProperties.getKafkaGroupId());
        //props.put (ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");

        return props;
    }

    @Bean
    public ConsumerFactory<String, User>  consumerFactory(){

        JsonDeserializer<User> deserializer = new JsonDeserializer<>(User.class,false);
        deserializer.addTrustedPackages("*");

        return new DefaultKafkaConsumerFactory<>(
                consumerConfigs(),
                new StringDeserializer(),
                deserializer
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,User>  kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String,User> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());

        return factory;
    }
}
