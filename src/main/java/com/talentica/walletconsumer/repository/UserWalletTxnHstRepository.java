package com.talentica.walletconsumer.repository;

import com.talentica.walletconsumer.entity.UserWalletTransactionHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("UserTxnHstRepo")
public interface UserWalletTxnHstRepository extends JpaRepository<UserWalletTransactionHistoryEntity,Long> {

//    @Query(nativeQuery = true,value = "select * from user_wallet_tx_hst where user_id = :userId")


}
