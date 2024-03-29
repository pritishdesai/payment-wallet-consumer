package com.talentica.walletconsumer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StripeDto {

    private String userName;
    private String cardNumber;
    private String expMonth;
    private String expYear;
    private String cvv;
    private String stripeToken;
    private Double amount;
    private Boolean success;
    private String message;
    private String chargeId;
    private Map<String,Object> additionalInfo = new HashMap<>();
}
