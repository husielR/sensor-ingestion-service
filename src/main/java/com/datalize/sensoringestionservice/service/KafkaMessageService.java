package com.datalize.sensoringestionservice.service;


import com.datalize.sensoringestionservice.dto.DtoSensor;
import com.datalize.sensoringestionservice.entity.HistoricSensorEntity;
import com.datalize.sensoringestionservice.mapper.HistoricSensorMapper;
import com.datalize.sensoringestionservice.repository.HistoricSensorRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
@Log4j2
public class KafkaMessageService {
    private final HistoricSensorRepository historicSensorRepository;
    private final HistoricSensorMapper historicSensorMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Value(value = "${spring.kafka.topic}")
    private String topic;

    public KafkaMessageService(
            HistoricSensorRepository historicSensorRepository,
            HistoricSensorMapper historicSensorMapper,
            KafkaTemplate<String, String> kafkaTemplate,
            ObjectMapper objectMapper)
            {
        this.historicSensorRepository = historicSensorRepository;
        this.historicSensorMapper = historicSensorMapper;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendMessage(String message) {
        kafkaTemplate.send(topic, message);
    }

    @KafkaListener(topics = "sensor-topic" , groupId = "kafka-group")
    public void sendDatabase(String message)   {

        DtoSensor dtoSensor = objectMapper.readValue(message, DtoSensor.class);
        log.info("Datos a ingestar: {}",dtoSensor.toString());
        HistoricSensorEntity historicSensorEntity = this.historicSensorMapper.toEntity(dtoSensor);
        this.historicSensorRepository.save(historicSensorEntity);

    }
}
