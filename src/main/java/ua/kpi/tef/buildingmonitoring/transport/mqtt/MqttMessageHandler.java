package ua.kpi.tef.buildingmonitoring.transport.mqtt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

@Component
public class MqttMessageHandler implements MessageHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MqttMessageHandler.class);

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        LOGGER.info("Received Message: " + message.getPayload().toString());


    }
}
