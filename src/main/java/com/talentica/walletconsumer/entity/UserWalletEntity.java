package com.talentica.walletconsumer.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_wallet")
@Data
public class UserWalletEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userWalletId;
    private Long userId;
    private BigDecimal balance;
    private LocalDateTime actionDate;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;
}
