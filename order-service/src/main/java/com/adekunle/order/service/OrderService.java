package com.adekunle.order.service;

import com.adekunle.commons.dtos.Requests.OrderRequestDto;
import com.adekunle.order.entity.PurchaseOrder;

public interface OrderService {
    PurchaseOrder createOrder(OrderRequestDto orderRequestDto);

}
