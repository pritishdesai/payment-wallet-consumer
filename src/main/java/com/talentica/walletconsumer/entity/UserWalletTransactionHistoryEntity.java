package com.talentica.walletconsumer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.sql.Update;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_wallet_tx_hst")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserWalletTransactionHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String userType;
    private String transactionType; // Credit Or Debit
    private BigDecimal amount;//Amount that is credited or debited.
    private String transactionId;

    @UpdateTimestamp
    private LocalDateTime actionDate;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdDate;
}
