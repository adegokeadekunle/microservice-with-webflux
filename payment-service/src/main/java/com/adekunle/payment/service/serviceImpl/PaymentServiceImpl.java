package com.adekunle.payment.service.serviceImpl;

import com.adekunle.commons.dtos.Requests.OrderRequestDto;
import com.adekunle.commons.dtos.Requests.PaymentRequestDto;
import com.adekunle.commons.enums.PaymentStatus;
import com.adekunle.commons.events.OrderEvent;
import com.adekunle.commons.events.PaymentEvent;
import com.adekunle.payment.entity.UserBalance;
import com.adekunle.payment.entity.UserTransaction;
import com.adekunle.payment.repository.UserBalanceRepository;
import com.adekunle.payment.repository.UserTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    
    private final UserBalanceRepository userBalanceRepository;
    private final UserTransactionRepository userTransactionRepository;

    //adding a dummy data in the database
    @PostConstruct
    void initUserBalanceInDB(){
        userBalanceRepository.saveAll(Stream.of( new UserBalance(101, BigDecimal.valueOf(5000)),
                        new UserBalance(102, BigDecimal.valueOf(3000)),
                        new UserBalance(103, BigDecimal.valueOf(9000)),
                        new UserBalance(104, BigDecimal.valueOf(6000)),
                        new UserBalance(105, BigDecimal.valueOf(4000))

        ).collect(Collectors.toList()));

    }

    @Override
    @Transactional
    public PaymentEvent newOrderEvent(OrderEvent orderEvent) {

        //get user id
        //then check balance availability
        //if balance is sufficient payment completed and deduct amount from db
        //if not sufficient, cancel order event and update the amount in db
        OrderRequestDto orderRequestDto = orderEvent.getOrderRequestDto();
        PaymentRequestDto paymentRequestDto = new PaymentRequestDto(orderRequestDto.getOrderId(),orderRequestDto.getAmount(),orderRequestDto.getUserId());

        return userBalanceRepository.findById(orderRequestDto.getUserId()).filter(userBalance -> userBalance.getPrice().compareTo(orderRequestDto.getAmount()) > orderRequestDto.getAmount().intValueExact())
                .map(userBalance ->{
                    userBalance.setPrice(userBalance.getPrice().subtract(orderRequestDto.getAmount()));
                    userTransactionRepository.save( UserTransaction.builder()
                                    .orderId(orderRequestDto.getOrderId())
                                    .amount(orderRequestDto.getAmount())
                                    .userId(orderRequestDto.getUserId())
                                    .productId(orderRequestDto.getProductId())
                                    .build());

                    return new PaymentEvent(paymentRequestDto, PaymentStatus.PAYMENT_SUCCESSFUL);

                }).orElse(new PaymentEvent(paymentRequestDto,PaymentStatus.PAYMENT_FAILED));

    }


    @Override
    @Transactional
    public void cancelOrderEvent(OrderEvent orderEvent) {

        userTransactionRepository.findById(orderEvent.getOrderRequestDto().getOrderId())
                .ifPresent(
                        userTransaction -> {
                            userTransactionRepository.delete(userTransaction);
                            userTransactionRepository.findById(userTransaction.getUserId())
                                    .ifPresent(userBalance -> userBalance.setAmount(userBalance.getAmount().add(userTransaction.getAmount())));
                        }
                );

    }
}
