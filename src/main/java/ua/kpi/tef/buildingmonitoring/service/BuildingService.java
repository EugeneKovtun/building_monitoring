package ua.kpi.tef.buildingmonitoring.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.transaction.Transactional;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ua.kpi.tef.buildingmonitoring.domain.Zone;
import ua.kpi.tef.buildingmonitoring.mapping.ZoneMapper;
import ua.kpi.tef.buildingmonitoring.persistence.ZoneEntity;
import ua.kpi.tef.buildingmonitoring.persistence.ZoneRepository;
import ua.kpi.tef.buildingmonitoring.transport.mqtt.MqttAdapter;

@Service
public class BuildingService {

    private final ZoneMapper zoneMapper;

    private final ZoneRepository zoneRepository;
    private final MqttAdapter mqttAdapter;

    public BuildingService(ZoneRepository zoneRepository, MqttAdapter mqttAdapter) {
        this.zoneRepository = zoneRepository;
        this.mqttAdapter = mqttAdapter;
        this.zoneMapper = Mappers.getMapper(ZoneMapper.class);
    }

    @Transactional
    public Zone updateZone(Zone zone, UUID uuid) {
        // TODO: 04.03.21 find Zone in DB
        // TODO: send request to mqtt
        // TODO: 04.03.21 return new entity


        ZoneEntity zoneEntity = zoneRepository.findByUuid(uuid).orElseThrow();
        mqttAdapter.updateZone(zone);
        prepareUpdatedZoneEntity(zone, zoneEntity);
        return zoneMapper.map(zoneRepository.save(zoneEntity));
    }

    @Transactional
    public Zone createZone(Zone zone) throws Exception {
        zone.setUuid(UUID.randomUUID());
        Zone resultZone = zoneMapper.map(zoneRepository.save(zoneMapper.map(zone)));
        mqttAdapter.initializeZone(resultZone);
        return resultZone;
    }


    @Transactional
    public List<Zone> getAllZones() {
        // TODO: 04.03.21 add permission check 

        return StreamSupport.stream(
                zoneRepository.findAll().spliterator(), false)
                .map(zoneMapper::map)
                .collect(Collectors.toList());
    }

    private void prepareUpdatedZoneEntity(Zone zone, ZoneEntity zoneEntity) {
        zoneEntity.setHumidity(zone.getHumidity());
        zoneEntity.setTemperature(zone.getTemperature());
        zoneEntity.setIsPublic(zone.getIsPublic());
        zoneEntity.setClientId(zone.getClientId());
    }

}
