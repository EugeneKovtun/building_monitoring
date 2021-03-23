package ua.kpi.tef.buildingmonitoring.persistence;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneRepository extends CrudRepository<ZoneEntity, Long> {
    Optional<ZoneEntity> findByUuid(UUID uuid);

    Optional<ZoneEntity> findByClientId(String clientId);
}
