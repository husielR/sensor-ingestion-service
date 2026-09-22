package com.datalize.sensoringestionservice.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SensorExternal {
    @JsonProperty("temperatura_c")
    private Double temperature;
    @JsonProperty("humedad_aire_pct")
    private Double humidity;
    @JsonProperty("humedad_suelo_raw")
    private Integer soilMoistureRaw;
}
