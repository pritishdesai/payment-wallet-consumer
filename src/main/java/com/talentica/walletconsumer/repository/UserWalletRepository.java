package com.talentica.walletconsumer.repository;

import com.talentica.walletconsumer.entity.UserEntity;
import com.talentica.walletconsumer.entity.UserWalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("UserWalletRepo")
public interface UserWalletRepository extends JpaRepository<UserWalletEntity, Long> {

    public UserEntity findByUserId(String userId);
}