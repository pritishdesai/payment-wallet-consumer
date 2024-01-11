package com.talentica.walletconsumer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTransactionHistoryDto {

    private String Id;
    private String userId;
    private String userType;
    private String txnType;
    private String amount;
    private LocalDateTime actionDate;
}
