package ua.kpi.tef.buildingmonitoring.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticRepository extends CrudRepository<StatisticEntity, Long> {
}