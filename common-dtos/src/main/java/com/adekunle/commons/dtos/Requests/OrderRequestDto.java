package com.adekunle.commons.dtos.Requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequestDto {

    private Integer userId;
    private Integer productId;
    private BigDecimal amount;
    private Integer orderId;
}
