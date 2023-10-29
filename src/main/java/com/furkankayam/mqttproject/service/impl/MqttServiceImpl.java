package com.furkankayam.mqttproject.service.impl;

import com.furkankayam.mqttproject.config.MqttConfig;
import com.furkankayam.mqttproject.model.MqttPublishModel;
import com.furkankayam.mqttproject.service.MqttService;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;

@Service
public class MqttServiceImpl implements MqttService {
    @Override
    public Void publishMessage(MqttPublishModel messagePublishModel) throws MqttException {

        String json = "{\"message\": \"" + messagePublishModel.getMessage() + "\"}";

        MqttMessage mqttMessage = new MqttMessage(json.getBytes());

        mqttMessage.setQos(messagePublishModel.getQos());
        mqttMessage.setRetained(messagePublishModel.getRetained());

        MqttConfig.getInstance().publish(messagePublishModel.getTopic(), mqttMessage);

        return null;
    }
}
