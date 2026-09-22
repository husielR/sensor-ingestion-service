package com.datalize.sensoringestionservice.business;

import com.datalize.sensoringestionservice.dto.DtoSensor;
import com.datalize.sensoringestionservice.pojo.SensorExternal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


class MapsTest {


    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        maps = new Maps(objectMapper);
    }

    private Maps maps;

    @Test
    void getSensorExternalCorrect() {
        String valueJson= "{\n" +
                "  \"temperatura_c\": 45,\n" +
                "  \"humedad_aire_pct\": 80,\n" +
                "  \"humedad_suelo_raw\": 1970\n" +
                "}";

        SensorExternal sensorExternalInput = new SensorExternal();
        sensorExternalInput.setTemperature(45.0);
        sensorExternalInput.setHumidity(80.0);
        sensorExternalInput.setSoilMoistureRaw(1970);

        SensorExternal expected = maps.getSensorExternal(valueJson);
        assertEquals(expected, sensorExternalInput);
    }

    @Test
    void getSensorExternalIncorrect() {
        String valueJson= "{\n" +
                "  \"temperatura\"45,\n" +
                "  \"humedadaire_porcentaje\": 80,\n" +
                "  \"humedad_sueloraw\" 1970\n" +
                "}";

        SensorExternal expected = maps.getSensorExternal(valueJson);
        assertNull(expected, "SensorExternal should be null" + expected);
    }

    @Test
    void fromSensorExternalToDtoSensor() {

        SensorExternal sensorExternalInput = new SensorExternal();
        sensorExternalInput.setTemperature(45.0);
        sensorExternalInput.setHumidity(80.0);
        sensorExternalInput.setSoilMoistureRaw(1970);

        DtoSensor expected = maps.fromSensorExternalToDtoSensor(sensorExternalInput);

        Assertions.assertEquals(sensorExternalInput.getTemperature(),expected.getTemperatura());
        Assertions.assertEquals(expected.getPercHumedadAmbiental(),sensorExternalInput.getHumidity());
        Assertions.assertEquals(sensorExternalInput.getSoilMoistureRaw(),expected.getHumedadsueloRaw());
        Assertions.assertEquals(23.23, expected.getPercHumedadTierra());
    }
}