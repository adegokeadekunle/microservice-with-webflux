package com.adekunle.payment.config;

import com.adekunle.commons.enums.OrderStatus;
import com.adekunle.commons.events.OrderEvent;
import com.adekunle.commons.events.PaymentEvent;
import com.adekunle.payment.service.serviceImpl.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
@RequiredArgsConstructor
public class PaymentConsumerConfig {

    private final PaymentService paymentService;

    public Function<Flux<OrderEvent>, Flux<PaymentEvent>> paymentProcessor(){
        return orderEventFlux -> orderEventFlux.flatMap(this::processPayment);
    }
    private Mono<PaymentEvent> processPayment(OrderEvent orderEvent){
        //get user id
        //then check balance availability
        //if balance is sufficient payment completed and deduct amount from db
        //if not sufficient, cancel order event and update the amount in db
        
        if (OrderStatus.ORDER_CREATED.equals(orderEvent.getOrderStatus())){
            return Mono.fromSupplier(()-> this.paymentService.newOrderEvent(orderEvent));
        }
        else{
            return Mono.fromRunnable(()-> this.paymentService.cancelOrderEvent(orderEvent));
        }

    }
}
