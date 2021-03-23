package ua.kpi.tef.buildingmonitoring.persistence;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity(name = "statistic")
public class StatisticEntity {
    @Id
    @GeneratedValue
    Long id;
    @ManyToOne(targetEntity = ZoneEntity.class)
    ZoneEntity zone;
    LocalDateTime dateTime;
    Integer temperature;
    Integer humidity;
    Integer actualTemperature;
    Integer actualHumidity;

}
