package com.talentica.walletconsumer.mapper;

import com.talentica.walletconsumer.dto.UserWalletTransactionHistoryDto;
import com.talentica.walletconsumer.entity.UserWalletTransactionHistoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserWalletTransactionHistoryMapper {

    UserWalletTransactionHistoryMapper USER_WALLET_TRANSACTION_HISTORY_MAPPER
            = Mappers.getMapper(UserWalletTransactionHistoryMapper.class);

    UserWalletTransactionHistoryEntity convertUserTransactionHistoryDtoToUserWalletTransactionHistoryEntity
            (UserWalletTransactionHistoryDto userTransactionHistoryDto);
}
