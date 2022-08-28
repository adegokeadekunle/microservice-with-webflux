package com.adekunle.order.service.serviceImpl;

import com.adekunle.commons.dtos.Requests.OrderRequestDto;
import com.adekunle.commons.enums.OrderStatus;
import com.adekunle.order.entity.PurchaseOrder;
import com.adekunle.order.repository.OrderRepository;
import com.adekunle.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl  implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderStatusPublisher orderStatusPublisher;

    @Override
    public PurchaseOrder createOrder(OrderRequestDto orderRequestDto) {

        boolean orderExist = orderRepository.existsById(orderRequestDto.getOrderId());
        if(orderExist) throw new IllegalArgumentException("Order Already made");
        PurchaseOrder order = PurchaseOrder.builder()
                .userId(orderRequestDto.getUserId())
                .productId(orderRequestDto.getProductId())
                .price(orderRequestDto.getAmount())
                .orderStatus(OrderStatus.ORDER_CREATED)
                .build();
        orderRepository.save(order);
        //produce kafka event with status ORDER_CREATED
        orderStatusPublisher.publishOrderEvent(orderRequestDto,OrderStatus.ORDER_CREATED);
        return order;
    }
}
