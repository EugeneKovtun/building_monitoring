package ua.kpi.tef.buildingmonitoring.persistence;

import java.time.LocalDateTime;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatisticEntity that = (StatisticEntity) o;
        return Objects.equals(zone, that.zone)
                && Objects.equals(temperature, that.temperature)
                && Objects.equals(humidity, that.humidity)
                && Objects.equals(actualTemperature, that.actualTemperature)
                && Objects.equals(actualHumidity, that.actualHumidity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zone, temperature, humidity, actualTemperature, actualHumidity);
    }
}
