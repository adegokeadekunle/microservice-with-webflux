package com.adekunle.order.config;

import com.adekunle.commons.enums.OrderStatus;
import com.adekunle.commons.enums.PaymentStatus;
import com.adekunle.order.entity.PurchaseOrder;
import com.adekunle.order.repository.OrderRepository;
import com.adekunle.order.service.serviceImpl.OrderStatusPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.transaction.Transactional;
import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class OrderStatusUpdateHandler {

    private final OrderRepository orderRepository;
    private final OrderStatusPublisher orderStatusPublisher;

    @Transactional
    public void updateOrder(int id, Consumer<PurchaseOrder> consumer){
        orderRepository.findById(id).ifPresent(consumer.andThen(this::updateOrder));

    }

    private void updateOrder(PurchaseOrder purchaseOrder) {
        boolean isOrderCompleted = PaymentStatus.PAYMENT_SUCCESSFUL.equals(purchaseOrder.getPaymentStatus());
        OrderStatus orderStatus = isOrderCompleted ? OrderStatus.ORDER_COMPLETED: OrderStatus.ORDER_CANCELLED;
        purchaseOrder.setOrderStatus(orderStatus);
       // if (isOrderCompleted)
    }
}
