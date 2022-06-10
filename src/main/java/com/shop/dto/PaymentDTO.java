package com.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDTO {

    private Long id;

    private OrderDetailsDTO order;

    private Double totalPrice;

    private String paymentType;

    private String status;

}
