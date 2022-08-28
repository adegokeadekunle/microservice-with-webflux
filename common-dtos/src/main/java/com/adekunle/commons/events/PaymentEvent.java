package com.adekunle.commons.events;

import com.adekunle.commons.dtos.Requests.OrderRequestDto;
import com.adekunle.commons.dtos.Requests.PaymentRequestDto;
import com.adekunle.commons.enums.OrderStatus;
import com.adekunle.commons.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
public class PaymentEvent implements Event{

    private UUID eventId= UUID.randomUUID();
    private Date eventDate  = new Date();
    private PaymentRequestDto paymentRequestDto;
    private PaymentStatus paymentStatus;

    @Override
    public UUID getEventId() {
        return eventId;
    }

    @Override
    public Date eventDate() {
        return eventDate;
    }

    public PaymentEvent(PaymentRequestDto paymentRequestDto, PaymentStatus paymentStatus){
        this.paymentRequestDto = paymentRequestDto;
        this.paymentStatus = paymentStatus;
    }

}
