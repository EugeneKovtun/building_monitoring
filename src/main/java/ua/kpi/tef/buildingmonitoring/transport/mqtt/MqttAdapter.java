package ua.kpi.tef.buildingmonitoring.transport.mqtt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ua.kpi.tef.buildingmonitoring.domain.Zone;
import ua.kpi.tef.buildingmonitoring.mapping.ZoneMapper;

@Component
public class MqttAdapter {

    private String publisherId = "monitoring-service";
    private IMqttClient publisher = new MqttClient("tcp://localhost:1883", publisherId);
    private ObjectMapper objectMapper = new ObjectMapper();
    private ZoneMapper zoneMapper;

    public MqttAdapter() throws MqttException {
        this.zoneMapper = Mappers.getMapper(ZoneMapper.class);

        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        publisher.connect(options);
    }

    public void initializeZone(Zone zone) throws MqttException, JsonProcessingException {
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setQos(1);
        ZoneMqtt zoneMqtt = zoneMapper.mapToZoneMqtt(zone);
        mqttMessage.setPayload(
                objectMapper
                        .writeValueAsString(zoneMqtt)
                        .getBytes(StandardCharsets.UTF_8));
        publisher.publish(zone.getTopic(), mqttMessage);
    }

    public Zone updateZone(Zone zone) {

        return zone;
    }

}
