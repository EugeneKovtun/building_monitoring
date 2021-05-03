package ua.kpi.tef.buildingmonitoring.transport.mqtt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;
import ua.kpi.tef.buildingmonitoring.service.StatisticService;

@Component
@AllArgsConstructor
public class MqttMessageHandler implements MessageHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MqttMessageHandler.class);
    private final ObjectMapper mapper = new ObjectMapper();
    private final StatisticService  statisticService;

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        LOGGER.info("Received Message: {}", message.getPayload());
        try {
            ZoneMqtt zoneMqtt = mapper.readValue(message.getPayload().toString(), ZoneMqtt.class);
            statisticService.saveStats(zoneMqtt);
        } catch (JsonProcessingException e) {
            LOGGER.error("Unable to parse message: {}", message.getPayload());
        }

    }
}
