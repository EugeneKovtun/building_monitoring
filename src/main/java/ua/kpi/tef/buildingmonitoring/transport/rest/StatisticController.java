package ua.kpi.tef.buildingmonitoring.transport.rest;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.kpi.tef.buildingmonitoring.domain.StatisticalState;
import ua.kpi.tef.buildingmonitoring.service.StatisticService;

@RestController
@AllArgsConstructor
@RequestMapping("/stats")
public class StatisticController {

    private final StatisticService statisticService;

    @GetMapping
    public List<StatisticalState> getAllStatistic() {
        return statisticService.getAll();
    }

    @GetMapping("{uuid}")
    public List<StatisticalState> getStatisticsPerZone(@PathVariable UUID uuid) {
        return statisticService.getStatisticPerRoom(uuid);
    }
    // TODO: 23.03.21 add statistic

}
