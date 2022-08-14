package com.smartech.sms.service.publisher;

import com.smartech.sms.config.ActiveMQConfiguration;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@AllArgsConstructor
public class MessageSender {

    private final JmsTemplate jmsTemplate;

    public void sendMessage(Message<?> message) {
        jmsTemplate.convertAndSend(ActiveMQConfiguration.SMS_QUEUE, message.getPayload());
    }

}
