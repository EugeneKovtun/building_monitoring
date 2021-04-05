package ua.kpi.tef.buildingmonitoring.persistence;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticRepository extends CrudRepository<StatisticEntity, Long> {
    List<StatisticEntity> findByZone(ZoneEntity zoneEntity);

    List<StatisticEntity> findByZoneAndDateTimeBetween(
            ZoneEntity zoneEntity,
            LocalDateTime startDate,
            LocalDateTime endDate);

    @Query(value = "select * from statistic where zone_id = ?1 order by date_time desc limit 2", nativeQuery = true)
    List<StatisticEntity> find2LastRecords(ZoneEntity zoneEntity);
}
