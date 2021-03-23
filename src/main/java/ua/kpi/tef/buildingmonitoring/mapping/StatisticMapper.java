package ua.kpi.tef.buildingmonitoring.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.kpi.tef.buildingmonitoring.domain.StatisticalState;
import ua.kpi.tef.buildingmonitoring.persistence.StatisticEntity;

@Mapper
public interface StatisticMapper {

    @Mapping(target = "clientId", source = "zone.clientId")
    StatisticalState map(StatisticEntity statisticEntity);
}
