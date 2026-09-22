package com.datalize.sensoringestionservice.business;


import com.datalize.sensoringestionservice.dto.DtoSensor;
import com.datalize.sensoringestionservice.pojo.SensorExternal;
import com.datalize.sensoringestionservice.util.Utils;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;


@Service
public class Maps {

    private final ObjectMapper objectMapper;

    public Maps(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    public SensorExternal getSensorExternal(String value){
        try {
            return objectMapper.readValue(value, SensorExternal.class);
        }catch (Exception e){
            return null;
        }
    }

    public DtoSensor fromSensorExternalToDtoSensor(SensorExternal sensorExternal){
        DtoSensor dtoSensor = new DtoSensor();
        dtoSensor.setPercHumedadAmbiental(sensorExternal.getHumidity());
        dtoSensor.setTemperatura(sensorExternal.getTemperature());
        dtoSensor.setHumedadsueloRaw(sensorExternal.getSoilMoistureRaw());
        dtoSensor.setPercHumedadTierra(Utils.getPercentileSoil(sensorExternal.getSoilMoistureRaw()));
        return dtoSensor;
    }
}
