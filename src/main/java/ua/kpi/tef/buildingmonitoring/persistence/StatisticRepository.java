package ua.kpi.tef.buildingmonitoring.persistence;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticRepository extends CrudRepository<StatisticEntity, Long> {
    List<StatisticEntity> findByZone(ZoneEntity zoneEntity);
}
