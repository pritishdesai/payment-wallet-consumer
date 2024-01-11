package com.talentica.walletconsumer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
public class UserEntity {

    @Id
    private Long userId;
    private String userName;
    private String userType;
    private LocalDateTime actionDate;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;
}
