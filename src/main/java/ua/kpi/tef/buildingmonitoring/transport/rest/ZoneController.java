package ua.kpi.tef.buildingmonitoring.transport.rest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ua.kpi.tef.buildingmonitoring.domain.Zone;
import ua.kpi.tef.buildingmonitoring.service.BuildingService;

@RestController
@AllArgsConstructor
@RequestMapping("/zone")
public class ZoneController {

    private final BuildingService buildingService;


    @PostMapping
    public Zone createZone(@RequestBody @Valid Zone zone) throws Exception {
        return buildingService.createZone(zone);
    }

    @PutMapping("/{uuid}")
    public Zone changeZoneParameters(@RequestBody Zone zone, @PathVariable UUID uuid) {
        validateZone(zone);
        try {
            return buildingService.updateZone(zone, uuid);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Zone with UUID:" + uuid + " not found.");
        }

    }

    @GetMapping
    public List<Zone> getAllZones() {
        return buildingService.getAllZones();
    }


    private void validateZone(Zone zone) {
        // TODO: 04.03.21 add validation
    }
}
