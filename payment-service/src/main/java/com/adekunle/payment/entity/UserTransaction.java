package com.adekunle.payment.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserTransaction {

    @Id
    private Integer orderId;
    private Integer userId;
    private BigDecimal amount;
    private Integer productId;
}
