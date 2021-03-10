package ua.kpi.tef.buildingmonitoring.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.kpi.tef.buildingmonitoring.domain.Zone;
import ua.kpi.tef.buildingmonitoring.persistence.ZoneRepository;
import ua.kpi.tef.buildingmonitoring.transport.mqtt.MqttAdapter;

@Service
@AllArgsConstructor
public class BuildingService {

    private final ZoneRepository zoneRepository;

    private final MqttAdapter mqttAdapter;

    @Transactional
    public Zone updateZone(Zone zone, UUID uuid) {
        // TODO: 04.03.21 find Zone in DB
        // TODO: send request to mqtt
        // TODO: 04.03.21 return new entity


        zoneRepository.findById(uuid).orElseThrow();
        mqttAdapter.updateZone(zone);
        return zoneRepository.save(zone);
    }

    @Transactional
    public Zone createZone(Zone zone) {
        mqttAdapter.initializeZone(zone);
        return zoneRepository.save(zone);
    }


    @Transactional
    public List<Zone> getAllZones() {
        // TODO: 04.03.21 add permission check 
        
        return StreamSupport.stream(
                zoneRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
