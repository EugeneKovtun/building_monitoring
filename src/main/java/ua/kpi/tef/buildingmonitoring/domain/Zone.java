package ua.kpi.tef.buildingmonitoring.domain;

import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Zone {
    private UUID uuid;
    private Integer temperature;
    private Integer humidity;
    @NotNull
    private String clientId;
    private Boolean isPublic = true;
}
