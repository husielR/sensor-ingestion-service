package com.datalize.sensoringestionservice.business;

import com.datalize.sensoringestionservice.dto.DtoSensor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class FilterTest {


    private Filter filter = new Filter();

    @Test
    void temperatureHigh() {
        double value = 70.0;
        DtoSensor sensor = new DtoSensor();
        sensor.setTemperatura(value);

        assertFalse(filter.temperatureCorrect(sensor));

    }

    @Test
    void temperatureLow() {
        double value = -45.0;
        DtoSensor sensor = new DtoSensor();
        sensor.setTemperatura(value);

        assertFalse(filter.temperatureCorrect(sensor));

    }

    @Test
    void temperatureCorrect() {
        double value = -9.0;
        DtoSensor sensor = new DtoSensor();
        sensor.setTemperatura(value);

        assertTrue(filter.temperatureCorrect(sensor));
    }

    @Test
    void humidityHight() {
        double value = 102;
        DtoSensor sensor = new DtoSensor();
        sensor.setPercHumedadAmbiental(value);

        assertFalse(filter.humidityCorrect(sensor));
    }
    @Test
    void humidityLow() {
        double value = -45.0;
        DtoSensor sensor = new DtoSensor();
        sensor.setPercHumedadAmbiental(value);
        assertFalse(filter.humidityCorrect(sensor));
    }
    @Test
    void humidityCorrect() {
        double value = 5.0;
        DtoSensor sensor = new DtoSensor();
        sensor.setPercHumedadAmbiental(value);
        assertTrue(filter.humidityCorrect(sensor));
    }

    @Test
    void temperatureNull() {

        DtoSensor sensor = new DtoSensor();
        sensor.setTemperatura(null);

        assertFalse(filter.temperatureCorrect(sensor));

    }

    @Test
    void humidityNull() {
        DtoSensor sensor = new DtoSensor();
        sensor.setPercHumedadAmbiental(null );
        assertFalse(filter.humidityCorrect(sensor));
    }

}