package com.adekunle.commons.events;

import com.adekunle.commons.dtos.Requests.OrderRequestDto;
import com.adekunle.commons.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent implements Event{

    private UUID eventId= UUID.randomUUID();
    private OrderRequestDto orderRequestDto;
    private OrderStatus orderStatus;
    private Date eventDate  = new Date();

    @Override
    public UUID getEventId() {
        return eventId;
    }

    @Override
    public Date eventDate() {
        return eventDate;
    }

    public OrderEvent(OrderRequestDto orderRequestDto, OrderStatus orderStatus){
        this.orderRequestDto = orderRequestDto;
        this.orderStatus = orderStatus;
    }
}
