package com.talentica.walletconsumer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StripeChargeDto {

    private String stripeToken;
    private String userName;
    private String amount;
    private Boolean success;
    private String message;
    private String chargeId;
    private Map<String,Object> additionalInfo = new HashMap<>();
}
