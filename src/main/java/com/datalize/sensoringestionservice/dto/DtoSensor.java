package com.datalize.sensoringestionservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class DtoSensor {
    private Double temperatura;
    private Double percHumedadAmbiental;
    private Double percHumedadTierra;
    private Integer humedadsueloRaw;
    @JsonIgnore
    private Double luminosidad;
    @JsonIgnore
    private Double peso;
}
