package ua.kpi.tef.buildingmonitoring.domain;

import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class Zone {
    private UUID uuid;
    private String alias;
    private BuildingParameters recommendedParameters;
    private List<BuildingParameters> historicalState;
}
