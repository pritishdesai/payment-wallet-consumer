package com.talentica.walletconsumer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserWalletDto {

    private String userWalletId;
    private String userId;
    private String userType;
    private String balance;


}
