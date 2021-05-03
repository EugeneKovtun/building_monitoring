package ua.kpi.tef.buildingmonitoring.transport.rest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public List<StatisticalState> getStatisticsPerZone(@PathVariable UUID uuid,
                                                       @RequestParam(required = false)
                                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                               LocalDateTime startDate,
                                                       @RequestParam(required = false)
                                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                               LocalDateTime endDate) {

        if (endDate == null) {
            endDate = LocalDateTime.now();
        }
        return statisticService.getStatisticPerRoom(uuid, startDate, endDate);
    }
}
