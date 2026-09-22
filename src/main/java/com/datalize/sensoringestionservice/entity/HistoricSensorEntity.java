package com.datalize.sensoringestionservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "historico_sensores")
public class HistoricSensorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "temperatura")
    private Double temperatura;

    @Column(name = "humedad_ambiente")
    private Double humedadAmbiente;

    @Column(name = "humedad_suelo")
    private Double humedadsuelo;

    @Column(name = "peso")
    private Double peso;

    @Column(name = "luminosidad")
    private Double luminosidad;

    @Column(name = "humedad_suelo_raw")
    private Integer humedadsueloRaw;
}
