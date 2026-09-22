package com.datalize.sensoringestionservice.repository;

import com.datalize.sensoringestionservice.entity.HistoricSensorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricSensorRepository extends JpaRepository<HistoricSensorEntity, Integer> {
}
