package com.sml.kafkareceiver.config;

import com.sml.kafkareceiver.config.deserializer.AvroDeserializer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nlmk.l3.sup.IntegralParameters;
import nlmk.l3.sup.UnrecoverableParametersTrends;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@EnableKafka
@Configuration
@RequiredArgsConstructor
public class UserConsumerConfig {

    private final ConsumerKafkaProperties consumerProperties;

    @Bean
    public Map<String,Object> consumerConfigs(){
        Map<String,Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, consumerProperties.getKafkaServer());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, AvroDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG,consumerProperties.getKafkaGroupId());
        props.put (ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

       // props.put("sasl.mechanism", "PLAIN");
     //   props.put("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule   required username='test_user'   password='<secret>';");
        //props.put("security.protocol", "SASL_SSL");
       // props.put("security.protocol", "SASL_PLAINTEXT");

        return props;
    }

    @Bean
    public ConsumerFactory<String, IntegralParameters>  consumerFactory(){

        return new DefaultKafkaConsumerFactory<>(
                consumerConfigs(),
                new StringDeserializer(),
                new AvroDeserializer<>(IntegralParameters.class)
        );
    }

    @Bean
    public ConsumerFactory<String, UnrecoverableParametersTrends>  consumerFactoryUP(){

        return new DefaultKafkaConsumerFactory<>(
                consumerConfigs(),
                new StringDeserializer(),
                new AvroDeserializer<>(UnrecoverableParametersTrends.class)
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,IntegralParameters>  kafkaListenerContainerFactory(){

        ConcurrentKafkaListenerContainerFactory<String,IntegralParameters> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(1); // устанавливается по количеству partitions в топике https://howtoprogram.xyz/2016/09/25/spring-kafka-multi-threaded-message-consumption/
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        factory.setErrorHandler(new SeekToCurrentErrorHandler(
                (record, error) -> {
                    log.error("--- ERROR: "+error.getMessage());
                    log.error("--- ERROR RECORD: "+record.toString());
                }, new FixedBackOff(5000L, 1))
        );
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,UnrecoverableParametersTrends>  kafkaListenerContainerFactoryUP(){
        ConcurrentKafkaListenerContainerFactory<String,UnrecoverableParametersTrends> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryUP());
        return factory;
    }
}
