package com.talentica.walletconsumer.mapper;

import com.talentica.walletconsumer.dto.UserWalletDto;
import com.talentica.walletconsumer.entity.UserWalletEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserWalletMapper {

    UserWalletMapper USER_WALLET_MAPPER = Mappers.getMapper(UserWalletMapper.class);

    UserWalletEntity convertUserWalletDtoToUserWalletEntity(UserWalletDto userWalletDto);

    UserWalletDto convertUserWalletEntityToUserWalletDto(UserWalletEntity userWalletEntity);
}
