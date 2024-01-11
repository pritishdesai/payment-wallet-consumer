package com.talentica.walletconsumer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersDto {

    private String userId;
    private String userName;
    private String userType;
    private List<StripeDto> stripeDetails;

}
