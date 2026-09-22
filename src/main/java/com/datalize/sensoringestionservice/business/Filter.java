package com.datalize.sensoringestionservice.business;

import com.datalize.sensoringestionservice.dto.DtoSensor;
import org.springframework.stereotype.Service;

@Service
public class Filter {

    public boolean temperatureCorrect(DtoSensor dtoSensor){
        Double temp = dtoSensor.getTemperatura();
        return temp != null && temp <= 61 && temp >= -10;    }

    public boolean humidityCorrect(DtoSensor dtoSensor){
        Double hume = dtoSensor.getPercHumedadAmbiental();
        return hume != null && hume <= 100 && hume >= 0;
    }
}
