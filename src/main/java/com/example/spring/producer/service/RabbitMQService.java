package com.example.spring.producer.service;

import com.example.spring.producer.amqp.AmqpProducer;
import com.example.spring.producer.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {

    @Autowired
    private AmqpProducer<MessageDTO> amqp;

    public  void sendToConsumer(MessageDTO messageDTO){
        amqp.producer(messageDTO);
    }

}
