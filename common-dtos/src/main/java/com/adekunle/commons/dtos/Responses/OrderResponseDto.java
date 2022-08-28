package com.adekunle.commons.dtos.Responses;

import com.adekunle.commons.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDto {

    private Integer userId;
    private Integer productId;
    private BigDecimal amount;
    private Integer orderId;
    private OrderStatus orderStatus;
}
