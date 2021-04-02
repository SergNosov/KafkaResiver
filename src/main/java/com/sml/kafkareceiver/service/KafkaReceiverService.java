package com.sml.kafkareceiver.service;

import com.sml.kafkareceiver.model.IntegralParam;
import com.sml.kafkareceiver.util.ParamConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nlmk.l3.sup.IntegralParameters;
import nlmk.l3.sup.UnrecoverableParametersTrends;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaReceiverService {

    private final IntegralParamService integralParamService;

    @KafkaListener(topics = {"${kafka.topicIP}"},
            containerFactory = "kafkaListenerContainerFactory")
    public void receiveMessage(@Payload IntegralParameters supIntegralParameters,
                               @Headers MessageHeaders headers,
                               Acknowledgment acknowledgment) {

        log.info("--- received integralParameters: {}", supIntegralParameters);
        headers.keySet().forEach(key -> {
            log.info("--- {}: {}", key, headers.get(key));
        });

        try {
            IntegralParam integralParam = ParamConverter.toIntegralParam(supIntegralParameters);

            if (integralParam.getRecordPk().intValue()==0) {
                buhBah();
            }

            System.out.println("--- integralParam: " + integralParam);
            acknowledgment.acknowledge();
        } catch (RuntimeException rte) {
            log.error("--- exception: " + rte.getMessage());
        }
    }

    private void buhBah() {
        throw new RuntimeException("!!!BUH-BAH!!!!");
    }

    @KafkaListener(topics = {"${kafka.topicUP}"},
            containerFactory = "kafkaListenerContainerFactoryUP")
    public void receiveMessageUP(@Payload UnrecoverableParametersTrends unrecoverableParameters,
                                 @Headers MessageHeaders headers) {
        log.info("--- received unrecoverableParameters: {}", unrecoverableParameters);
        headers.keySet().forEach(key -> {
            log.info("--- {}: {}", key, headers.get(key));
        });
    }
}
