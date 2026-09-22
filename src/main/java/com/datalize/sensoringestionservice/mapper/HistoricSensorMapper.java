package com.datalize.sensoringestionservice.mapper;

import com.datalize.sensoringestionservice.dto.DtoSensor;
import com.datalize.sensoringestionservice.entity.HistoricSensorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HistoricSensorMapper {
    @Mapping(source = "percHumedadAmbiental", target = "humedadAmbiente")
    @Mapping(source = "percHumedadTierra", target = "humedadsuelo")
    HistoricSensorEntity toEntity(DtoSensor request);
}
