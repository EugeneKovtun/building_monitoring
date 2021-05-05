package ua.kpi.tef.buildingmonitoring.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.transaction.Transactional;
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

    @Transactional
    public void saveStats(ZoneMqtt zoneMqtt) {
        ZoneEntity zoneEntity = zoneRepository.findByClientId(zoneMqtt.getClientId()).orElseThrow();
        StatisticEntity statisticEntity = createStatisticEntity(zoneMqtt, zoneEntity);

        List<StatisticEntity> lastRecords = statisticRepository.find2LastRecords(zoneEntity);
        saveStatisticEntityIfNeeded(statisticEntity, lastRecords);
    }

    private void saveStatisticEntityIfNeeded(StatisticEntity statisticEntity, List<StatisticEntity> lastRecords) {
        if (lastRecords.size() < 2) {
            statisticRepository.save(statisticEntity);
            return;
        }
        if (statisticEntity.equals(lastRecords.get(0)) && statisticEntity.equals(lastRecords.get(1))) {
            statisticRepository.delete(lastRecords.get(0));
            statisticRepository.save(statisticEntity);
            return;
        }
        statisticRepository.save(statisticEntity);
    }

    private StatisticEntity createStatisticEntity(ZoneMqtt zoneMqtt, ZoneEntity zoneEntity) {
        StatisticEntity statisticEntity = new StatisticEntity();
        statisticEntity.setHumidity(zoneEntity.getHumidity());
        statisticEntity.setTemperature(zoneEntity.getTemperature());

        statisticEntity.setActualHumidity(zoneMqtt.getHumidity());
        statisticEntity.setActualTemperature(zoneMqtt.getTemperature());

        statisticEntity.setDateTime(LocalDateTime.now());
        statisticEntity.setZone(zoneEntity);
        return statisticEntity;
    }

    public List<StatisticalState> getAll() {
        return StreamSupport.stream(
                statisticRepository.findAll().spliterator(), true)
                .map(statisticMapper::map).collect(Collectors.toList());
    }

    public List<StatisticalState> getStatisticPerRoom(UUID uuid, LocalDateTime startDate, LocalDateTime endDate) {


        List<StatisticalState> statisticalStates = statisticRepository.findByZone(zoneRepository.findByUuid(uuid).get())
                .stream()
                .map(statisticMapper::map)
                .sorted(Comparator.comparing(StatisticalState::getDateTime))
                .collect(Collectors.toList());

        List<StatisticalState> toBeRemoved = new ArrayList<>();
        for (int i = 1; i < statisticalStates.size()-1; i++) {
            if (startDate != null && statisticalStates.get(i).getDateTime().compareTo(startDate) < 0) {
                toBeRemoved.add(statisticalStates.get(i-1));
            }
            if (endDate != null && statisticalStates.get(i).getDateTime().compareTo(endDate) > 0) {
                toBeRemoved.add(statisticalStates.get(i+1));
            }
        }

        toBeRemoved.forEach(statisticalStates::remove);
        return statisticalStates;
    }
}
