package com.talentica.walletconsumer.dto;

import lombok.Data;

@Data
public class UserTransactionHistoryDto {

    private String Id;
    private String userId;
    private String userType;
    private String txnType;
    private String amount;
}
