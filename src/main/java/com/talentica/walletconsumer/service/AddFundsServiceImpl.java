package com.talentica.walletconsumer.service;

import com.talentica.walletconsumer.constants.AppConstants;
import com.talentica.walletconsumer.dto.*;
import com.talentica.walletconsumer.entity.UserEntity;
import com.talentica.walletconsumer.mapper.UserMapper;
import com.talentica.walletconsumer.mapper.UserWalletMapper;
import com.talentica.walletconsumer.mapper.UserWalletTransactionHistoryMapper;
import com.talentica.walletconsumer.repository.UserRepository;
import com.talentica.walletconsumer.repository.UserWalletRepository;
import com.talentica.walletconsumer.repository.UserWalletTransactionHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class AddFundsServiceImpl implements AddFundsService{

    private final StripeServiceImpl stripeService;
    private final UserWalletRepository userWalletRepository;
    private final UserRepository userRepository;
    private final UserWalletTransactionHistoryRepository userWalletTransactionHistoryRepository;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void addFunds(AddWithdrawFundsDto addWithdrawFundsDto) {

        StripeChargeDto stripeChargeDto =
                StripeChargeDto.builder()
                        .stripeToken(addWithdrawFundsDto.getStripeDetails().getStripeToken())
                        .amount(addWithdrawFundsDto.getAmount())
                        .chargeId(addWithdrawFundsDto.getStripeDetails().getChargeId())
                        .additionalInfo(addWithdrawFundsDto.getStripeDetails().getAdditionalInfo())
                        .build();

        if(stripeService.charge(stripeChargeDto).getSuccess()){
            UserWalletDto userWalletDto = UserWalletMapper
                    .USER_WALLET_MAPPER
                    .convertUserWalletEntityToUserWalletDto(
                            userWalletRepository
                                    .findByUserId(Long.valueOf(addWithdrawFundsDto.getUserId())));
            log.info(String.format("AddFundsServiceImpl::addFunds -> User Wallet Before Update ==> %s",
                    userWalletDto));

            userWalletDto.setBalance(
                    String.valueOf(
                            new BigDecimal(userWalletDto.getBalance())
                                    .add(new BigDecimal(addWithdrawFundsDto.getAmount()))));
            log.info(String.format("AddFundsServiceImpl::addFunds -> User Wallet After Update ==> %s",
                    userWalletDto));
            userWalletRepository.save(UserWalletMapper
                                        .USER_WALLET_MAPPER
                                        .convertUserWalletDtoToUserWalletEntity(userWalletDto));
            log.info(String.format("AddFundsServiceImpl::addFunds -> User Wallet Balance Update saved in DB ==> %s",
                    userWalletDto));

            UsersDto usersDto = UserMapper.USER_MAPPER
                    .convertUserEntityToUsersDto(
                            userRepository
                                    .findById(Long.parseLong(addWithdrawFundsDto.getUserId()))
                                    .orElseGet(UserEntity::new));

            UserWalletTransactionHistoryDto userWalletTransactionHistoryDto =
                    UserWalletTransactionHistoryDto
                            .builder()
                            .userId(Long.parseLong(addWithdrawFundsDto.getUserId()))
                            .amount(addWithdrawFundsDto.getAmount())
                            .userType(usersDto.getUserType())
                            .transactionType(AppConstants.WALLET_TRANSACTION_TYPE_CREDIT)
                            .actionDate(LocalDateTime.now())
                            .build();

            userWalletTransactionHistoryRepository.save(
                    UserWalletTransactionHistoryMapper.USER_WALLET_TRANSACTION_HISTORY_MAPPER
                            .convertUserWalletTransactionHistoryDtoToUserWalletTransactionHistoryEntity(userWalletTransactionHistoryDto));
            log.info(String.format("AddFundsServiceImpl::addFunds -> Logged Transaction %s",
                    userWalletTransactionHistoryDto.toString()));



        }

    }
}
