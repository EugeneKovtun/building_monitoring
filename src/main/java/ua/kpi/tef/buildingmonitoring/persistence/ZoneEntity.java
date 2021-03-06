package ua.kpi.tef.buildingmonitoring.persistence;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Entity(name = "zone")
@Data
public class ZoneEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(updatable = false, unique = true, nullable = false)
    private UUID uuid;
    @Column(updatable = false, unique = true, nullable = false)
    private String clientId;
    private Integer temperature;
    private Integer humidity;
}
