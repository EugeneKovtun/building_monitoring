package ua.kpi.tef.buildingmonitoring.transport.mqtt;

import lombok.Data;

@Data
public class ZoneMqtt {
    private Integer temperature;
    private Integer humidity;
}
