package com.talentica.walletconsumer.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_wallet_tx_hst")
public class UserWalletTransactionHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String userType;
    private String transactionType; // Credit Or Debit
    private BigDecimal amount; //Amount that is credited or debited.

    @UpdateTimestamp
    private LocalDateTime actionDate;
    @CreationTimestamp
    private LocalDateTime createdDate;
}
