package com.datalize.sensoringestionservice.util;

import org.apache.kafka.common.serialization.Serde;
import org.springframework.kafka.support.serializer.JacksonJsonSerde;

public class Utils {

    public static <T> Serde<T> jacksonMapper (Class<T> valueClass ){
        return new JacksonJsonSerde<>(valueClass);
    }

    public static Double getPercentileSoil (Integer value){

        double percentage = ((2412 - value) / 1903.0) * 100;
        double clampedValue = Math.max(0, Math.min(100, percentage));

        return Math.round(clampedValue * 100.0) / 100.0;
    }
}
