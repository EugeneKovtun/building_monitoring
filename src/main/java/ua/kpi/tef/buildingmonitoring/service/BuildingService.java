package ua.kpi.tef.buildingmonitoring.service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.transaction.Transactional;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
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
    public Zone updateZone(Zone zone, UUID uuid) throws Exception {


        ZoneEntity zoneEntity = zoneRepository.findByUuid(uuid).orElseThrow();
        mqttAdapter.setZoneParameters(zone);
        prepareUpdatedZoneEntity(zone, zoneEntity);
        return zoneMapper.map(zoneRepository.save(zoneEntity));
    }

    @Transactional
    public Zone createZone(Zone zone) throws Exception {
        zone.setUuid(UUID.randomUUID());
        Zone resultZone = zoneMapper.map(zoneRepository.save(zoneMapper.map(zone)));
        mqttAdapter.setZoneParameters(resultZone);
        return resultZone;
    }


    @Transactional
    public List<Zone> getAllZones() {
        return StreamSupport.stream(
                zoneRepository.findAll().spliterator(), false)
                .map(zoneMapper::map)
                .sorted(Comparator.comparing(Zone::getName))
                .collect(Collectors.toList());
    }

    private void prepareUpdatedZoneEntity(Zone zone, ZoneEntity zoneEntity) {
        zoneEntity.setHumidity(zone.getHumidity());
        zoneEntity.setTemperature(zone.getTemperature());
        zoneEntity.setClientId(zone.getClientId());
    }

    public Zone getZone(UUID uuid) {
        return zoneRepository.findByUuid(uuid).map(zoneMapper::map)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Zone with uuid " + uuid + " not found"));
    }

    @Transactional
    public void deleteZone(UUID uuid) {
        zoneRepository.deleteByUuid(uuid);
    }
}
