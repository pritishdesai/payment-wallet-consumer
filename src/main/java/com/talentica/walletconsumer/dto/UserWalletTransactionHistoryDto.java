package com.talentica.walletconsumer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserWalletTransactionHistoryDto {

    private String id;
    private String userId;
    private String userType;
    private String transactionId;
    private String transactionType;
    private String amount;
    private LocalDateTime actionDate;
}
