package ua.kpi.tef.buildingmonitoring.persistence;

import java.time.LocalDateTime;
import javax.persistence.Table;
import lombok.Data;

@Data
@Table(name = "statistic")
public class StatisticEntity {
    ZoneEntity zone;
    LocalDateTime dateTime;
    Integer temperature;
    Integer humidity;
    Integer actualTemperature;
    Integer actualHumidity;

}
