package com.datalize.sensoringestionservice.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UtilsTest {

    @Test
    void getPercentileSoilDry() {
        Integer value = 2412;
        double valueResult = Utils.getPercentileSoil(value);
        Assertions.assertEquals(0.0, valueResult);
    }

    @Test
    void getPercentileSoilWet() {
        Integer value = 509;
        double valueResult = Utils.getPercentileSoil(value);
        Assertions.assertEquals(100.0, valueResult);

    }

    @Test
    void getPercentileSoilMedium() {
        Integer value = 1461;
        double valueResult = Utils.getPercentileSoil(value);
        Assertions.assertEquals(49.97, valueResult);

    }

    @Test
    void getPercentileSoilStable() {
        Integer value = 9999999;
        double valueResult = Utils.getPercentileSoil(value);
        Assertions.assertEquals(0.0, valueResult);
    }

}