package com.talentica.walletconsumer.repository;

import com.talentica.walletconsumer.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("UserRepo")
public interface UserRepository  extends JpaRepository<UserEntity,Long> {

}
