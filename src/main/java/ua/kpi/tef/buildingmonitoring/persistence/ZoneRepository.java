package ua.kpi.tef.buildingmonitoring.persistence;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.kpi.tef.buildingmonitoring.domain.Zone;

@Repository
public interface ZoneRepository extends CrudRepository <Zone, UUID>{
}
