package com.adekunle.order.controller;

import com.adekunle.commons.dtos.Requests.OrderRequestDto;
import com.adekunle.order.entity.PurchaseOrder;
import com.adekunle.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create-order")
    @ResponseStatus(HttpStatus.CREATED)
    public PurchaseOrder createOrder(@RequestBody OrderRequestDto orderRequestDto) {

        return  orderService.createOrder(orderRequestDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all-orders")
    public List<PurchaseOrder> getAllOrders() {
        return orderService.getAllOrders();

        }
}
