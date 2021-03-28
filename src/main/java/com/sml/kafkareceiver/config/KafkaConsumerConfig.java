package com.sml.kafkareceiver.config;

import com.sml.kafkareceiver.model.User;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.ShortDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

//@EnableKafka
//@Configuration
public class KafkaConsumerConfig {
//
//    private final ConsumerKafkaProperties consumerKafkaProperties;
//
//    public KafkaConsumerConfig(ConsumerKafkaProperties consumerKafkaProperties) {
//        this.consumerKafkaProperties = consumerKafkaProperties;
//    }
//
//    @Bean
//    public Map<String,Object> consumerConfigs(){
//        Map<String,Object> config=new HashMap<>();
//
//        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, consumerKafkaProperties.getKafkaServer());
//        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,JsonDeserializer.class);
//        config.put(ConsumerConfig.GROUP_ID_CONFIG,consumerKafkaProperties.getKafkaGroupId());
//        config.put (ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
//
//        return config;
//    }
//
//    @Bean
//    public ConsumerFactory<String,User> consumerFactory() {
//        return new DefaultKafkaConsumerFactory<>(
//                consumerConfigs(),
//                new StringDeserializer(),
//                new JsonDeserializer<>(User.class)
//        );
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, User> listenerContainerFactory(){
//        ConcurrentKafkaListenerContainerFactory<String,User> factory =
//                new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        return factory;
//    }
}
