package ua.kpi.tef.buildingmonitoring.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ua.kpi.tef.buildingmonitoring.domain.StatisticalState;
import ua.kpi.tef.buildingmonitoring.mapping.StatisticMapper;
import ua.kpi.tef.buildingmonitoring.persistence.StatisticEntity;
import ua.kpi.tef.buildingmonitoring.persistence.StatisticRepository;
import ua.kpi.tef.buildingmonitoring.persistence.ZoneEntity;
import ua.kpi.tef.buildingmonitoring.persistence.ZoneRepository;
import ua.kpi.tef.buildingmonitoring.transport.mqtt.ZoneMqtt;

@Service
@AllArgsConstructor
public class StatisticService {

    private final ZoneRepository zoneRepository;
    private final StatisticRepository statisticRepository;
    private final StatisticMapper statisticMapper = Mappers.getMapper(StatisticMapper.class);

    public void saveStats(ZoneMqtt zoneMqtt) {
        ZoneEntity zoneEntity = zoneRepository.findByClientId(zoneMqtt.getClientId()).orElseThrow();
        StatisticEntity statisticEntity = new StatisticEntity();
        statisticEntity.setHumidity(zoneEntity.getHumidity());
        statisticEntity.setTemperature(zoneEntity.getTemperature());
        statisticEntity.setActualHumidity(zoneMqtt.getHumidity());
        statisticEntity.setActualTemperature(zoneEntity.getTemperature());
        statisticEntity.setDateTime(LocalDateTime.now());
        statisticEntity.setZone(zoneEntity);

        statisticRepository.save(statisticEntity);
    }

    public List<StatisticalState> getAll() {
        return StreamSupport.stream(
                statisticRepository.findAll().spliterator(), true)
                .map(statisticMapper::map).collect(Collectors.toList());
    }
}
