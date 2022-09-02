package com.adekunle.payment.service.serviceImpl;

import com.adekunle.commons.events.OrderEvent;
import com.adekunle.commons.events.PaymentEvent;

public interface PaymentService {
    PaymentEvent newOrderEvent(OrderEvent orderEvent);

    void cancelOrderEvent(OrderEvent orderEvent);
}
