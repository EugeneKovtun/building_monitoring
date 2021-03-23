package ua.kpi.tef.buildingmonitoring.transport.rest;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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
    // TODO: 23.03.21 add statistic

}
