package ua.kpi.tef.buildingmonitoring.domain;

import java.util.Map;
import lombok.Data;

@Data
public class BuildingParameters {
    private Integer temperature;
    private Integer humidity;
    private Map<String, String> additions;
    private Boolean isPublic = true;
}
