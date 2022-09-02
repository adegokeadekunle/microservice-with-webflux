package com.adekunle.order.service;

import com.adekunle.commons.dtos.Requests.OrderRequestDto;
import com.adekunle.order.entity.PurchaseOrder;

import java.util.List;

public interface OrderService {
    PurchaseOrder createOrder(OrderRequestDto orderRequestDto);
    List<PurchaseOrder> getAllOrders();

}
