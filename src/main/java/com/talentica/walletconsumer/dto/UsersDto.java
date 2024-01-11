package com.talentica.walletconsumer.dto;

import lombok.Data;

import java.util.List;

@Data
public class UsersDto {

    private String userId;
    private String userName;
    private String userType;
    private List<StripeDto> stripeDetails;

}
