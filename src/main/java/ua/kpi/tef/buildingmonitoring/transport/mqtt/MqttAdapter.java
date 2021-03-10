package ua.kpi.tef.buildingmonitoring.transport.mqtt;

import java.util.UUID;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Component;
import ua.kpi.tef.buildingmonitoring.domain.Zone;

@Component
public class MqttAdapter {

    String publisherId = UUID.randomUUID().toString();
    IMqttClient publisher = new MqttClient("tcp://localhost:1883",publisherId);

    public MqttAdapter() throws MqttException {
    }

    public Zone initializeZone(Zone zone) {
        // TODO: 04.03.21 add mqtt communication
        return zone;
    }

    public Zone updateZone(Zone zone) {

        return zone;
    }

}
