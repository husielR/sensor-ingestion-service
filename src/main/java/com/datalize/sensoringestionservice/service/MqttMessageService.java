package com.datalize.sensoringestionservice.service;


import lombok.extern.log4j.Log4j2;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class MqttMessageService {

    private final KafkaMessageService kafkaMessageService;

    public MqttMessageService(KafkaMessageService kafkaMessageService) {
        this.kafkaMessageService = kafkaMessageService;
    }

    @ServiceActivator(inputChannel = "mqttInputChannel")
    public void handleMessage(Message<?> message) {

        log.info("Received Message: {}", message.getPayload());
        kafkaMessageService.sendMessage(message.getPayload().toString());
    }


}
