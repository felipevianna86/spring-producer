package com.example.spring.producer.amqp.impl;

import com.example.spring.producer.amqp.AmqpProducer;
import com.example.spring.producer.dto.MessageDTO;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProducerRabbitMQImpl implements AmqpProducer<MessageDTO> {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.request.routing-key.producer}")
    private String queue;

    @Value("${spring.rabbitmq.request.exchange.producer}")
    private String exchange;

    @Override
    public void producer(MessageDTO messageDTO) {

        try {
            rabbitTemplate.convertAndSend(exchange, queue, messageDTO);
        }catch (Exception e){
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
