package com.talentica.walletconsumer.mapper;

import com.talentica.walletconsumer.dto.UsersDto;
import com.talentica.walletconsumer.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    UserEntity convertUsersDtoToUserEntity(UsersDto usersDto);

    UsersDto convertUserEntityToUsersDto(UserEntity userEntity);
}
