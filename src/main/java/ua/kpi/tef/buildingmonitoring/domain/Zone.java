package ua.kpi.tef.buildingmonitoring.domain;

import java.util.UUID;
import lombok.Data;

@Data
public class Zone {
    private UUID uuid;
    private Integer temperature;
    private Integer humidity;
    private Boolean isPublic = true;
}
