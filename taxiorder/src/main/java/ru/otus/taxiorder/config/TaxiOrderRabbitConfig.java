package ru.otus.taxiorder.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;

import static ru.otus.common.config.CommonRabbitConfig.*;

@Configuration
public class TaxiOrderRabbitConfig implements RabbitListenerConfigurer {

    @Bean
    Queue orderRequestQueue() {
        return QueueBuilder.durable(QUEUE_ORDER_REQUEST).build();
    }

    @Bean
    Queue orderOrderQueue() {
        return QueueBuilder.durable(QUEUE_ORDER_ORDER).build();
    }

    @Bean
    Queue orderCancelQueue() {
        return QueueBuilder.durable(QUEUE_ORDER_CANCEL).build();
    }

    @Bean
    Queue getClientInfoQueue() {
        return QueueBuilder.durable(QUEUE_CLIENTS_INFO).build();
    }

    @Bean
    Queue takeOrderQueue() {
        return QueueBuilder.durable(QUEUE_TAXI_TAKE_ORDER).build();
    }

    @Bean
    Queue getTaxiInfoQueue() {
        return QueueBuilder.durable(QUEUE_TAXI_INFO).build();
    }

    @Bean
    Queue taxiIsBusyQueue() {
        return QueueBuilder.durable(QUEUE_TAXI_IS_BUSY).build();
    }

    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }

    @Bean
    MessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory messageHandlerMethodFactory = new DefaultMessageHandlerMethodFactory();
        messageHandlerMethodFactory.setMessageConverter(consumerJackson2MessageConverter());
        return messageHandlerMethodFactory;
    }

    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }
}