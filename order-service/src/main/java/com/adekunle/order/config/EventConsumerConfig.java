package com.adekunle.order.config;

import com.adekunle.commons.events.PaymentEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class EventConsumerConfig {

    @Bean
    public Consumer<PaymentEvent> paymentEventConsumer(){
        // TODO: 9/2/22
        //listen to payment invent topic
        //check payment status
        //if payment status is completed  -> complete the order
        //if payment status is failed -> cancel the order

    }
}
