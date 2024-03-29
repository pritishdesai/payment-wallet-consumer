package com.talentica.walletconsumer.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
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
    private String userType;
    private BigDecimal balance;
    @UpdateTimestamp
    private LocalDateTime actionDate;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdDate;
}
