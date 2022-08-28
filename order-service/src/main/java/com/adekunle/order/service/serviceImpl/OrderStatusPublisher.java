package com.adekunle.order.service.serviceImpl;

import com.adekunle.commons.dtos.Requests.OrderRequestDto;
import com.adekunle.commons.enums.OrderStatus;
import com.adekunle.commons.events.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

@Service
@RequiredArgsConstructor
public class OrderStatusPublisher {

    public final Sinks.Many <OrderEvent> orderSinks;

    public void publishOrderEvent(OrderRequestDto orderRequestDto, OrderStatus orderStatus){
        OrderEvent orderEvent = new OrderEvent(orderRequestDto,orderStatus);
        orderSinks.tryEmitNext(orderEvent);
    }
}
