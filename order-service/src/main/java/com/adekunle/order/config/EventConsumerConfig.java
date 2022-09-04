package com.adekunle.order.config;

import com.adekunle.commons.events.PaymentEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class EventConsumerConfig {

    private final OrderStatusUpdateHandler handler;

    @Bean
    public Consumer<PaymentEvent> paymentEventConsumer(){
        // TODO: 9/2/22
        //listen to payment invent topic
        //check payment status
        //if payment status is completed  -> complete the order
        //if payment status is failed -> cancel the order

        return (paymentEvent)-> handler.updateOrder(paymentEvent.getPaymentRequestDto().getOrderId(),
                purchaseOrder -> {purchaseOrder.setPaymentStatus(paymentEvent.getPaymentStatus());
        });

    }
}
