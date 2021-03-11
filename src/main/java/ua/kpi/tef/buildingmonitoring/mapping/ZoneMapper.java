package ua.kpi.tef.buildingmonitoring.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.kpi.tef.buildingmonitoring.domain.Zone;
import ua.kpi.tef.buildingmonitoring.persistence.ZoneEntity;
import ua.kpi.tef.buildingmonitoring.transport.mqtt.ZoneMqtt;

@Mapper
public interface ZoneMapper {

    Zone map(ZoneEntity zoneEntity);

    @Mapping(target = "id", ignore = true)
    ZoneEntity map(Zone zone);

    ZoneMqtt mapToZoneMqtt(Zone zone);
}
