package ru.otus.apigateway.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;

import static ru.otus.common.config.CommonRabbitConfig.*;

@Configuration
public class ApiGatewayRabbitConfig implements RabbitListenerConfigurer {

    @Bean
    Queue clientsQueue() {
        return QueueBuilder.durable(QUEUE_CLIENTS).build();
    }

    @Bean
    Queue taxiQueue() {
        return QueueBuilder.durable(QUEUE_TAXI).build();
    }

    @Bean
    Queue taxiStartWorkQueue() {
        return QueueBuilder.durable(QUEUE_TAXI_START_WORK).build();
    }

    @Bean
    Queue taxiLocationQueue() {
        return QueueBuilder.durable(QUEUE_TAXI_LOCATION).build();
    }

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
    Queue takeOrderQueue() {
        return QueueBuilder.durable(QUEUE_TAXI_TAKE_ORDER).build();
    }

    @Bean
    Queue taxiStatusQueue() {
        return QueueBuilder.durable(QUEUE_TAXI_STATUS).build();
    }

    @Bean
    Queue getCarInfoQueue() {
        return QueueBuilder.durable(QUEUE_GET_CAR_INFO).build();
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