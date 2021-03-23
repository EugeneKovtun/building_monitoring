package ua.kpi.tef.buildingmonitoring.domain;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class StatisticalState {
    String clientId;
    LocalDateTime dateTime;
    Integer temperature;
    Integer humidity;
    Integer actualTemperature;
    Integer actualHumidity;
}
