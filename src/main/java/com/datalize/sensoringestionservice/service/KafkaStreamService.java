package com.datalize.sensoringestionservice.service;

import com.datalize.sensoringestionservice.business.Filter;
import com.datalize.sensoringestionservice.business.Maps;
import com.datalize.sensoringestionservice.dto.DtoSensor;
import com.datalize.sensoringestionservice.pojo.SensorExternal;
import com.datalize.sensoringestionservice.util.Utils;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;


@Configuration(proxyBeanMethods = false)
@EnableKafkaStreams
@Log4j2
public class KafkaStreamService {

    @Value(value = "${spring.kafka.topic}")
    private String topic;

    @Bean
    public KStream<String, SensorExternal> kStreamPipe(StreamsBuilder streamsBuilder, Filter filter, Maps maps) {
        KStream<String, String> rawStream = streamsBuilder.stream(topic, Consumed.with(Serdes.String(), Serdes.String()));

        KStream<String, SensorExternal> stream = rawStream
                .mapValues(maps::getSensorExternal)
                .filter((key, value) -> value != null);

        stream
                .mapValues(maps::fromSensorExternalToDtoSensor)
                .filter((key, value) -> filter.temperatureCorrect(value))
                .filter((key, value) -> filter.humidityCorrect(value))
                .to("sensor-topic", Produced.with(Serdes.String(), Utils.jacksonMapper(DtoSensor.class)));

        return stream;
    }

}
