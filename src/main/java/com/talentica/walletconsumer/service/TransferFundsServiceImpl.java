package com.talentica.walletconsumer.service;

import com.talentica.walletconsumer.constants.AppConstants;
import com.talentica.walletconsumer.dto.TransferRequestDto;
import com.talentica.walletconsumer.dto.UserWalletTransactionHistoryDto;
import com.talentica.walletconsumer.dto.UserWalletDto;
import com.talentica.walletconsumer.dto.UsersDto;
import com.talentica.walletconsumer.entity.UserEntity;
import com.talentica.walletconsumer.entity.UserWalletTransactionHistoryEntity;
import com.talentica.walletconsumer.mapper.UserMapper;
import com.talentica.walletconsumer.mapper.UserWalletMapper;
import com.talentica.walletconsumer.repository.UserRepository;
import com.talentica.walletconsumer.repository.UserWalletRepository;
import com.talentica.walletconsumer.repository.UserWalletTransactionHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service("transferFundsServiceImpl")
@Slf4j
@RequiredArgsConstructor
public class TransferFundsServiceImpl implements TransferFundsService{

    private final UserWalletRepository userWalletRepository;
    private final UserWalletTransactionHistoryRepository userWalletTxnHstRepository;
    private final UserRepository userRepository;
    @Override
    public void transferFunds(TransferRequestDto transferRequestDto) {

        log.info(String.format("TransferFundsServiceImpl::transferFunds received transfer request %s", transferRequestDto.toString()));
        //1.Get Balance for both sender and receiver
        UserWalletDto senderWalletDto = UserWalletMapper.USER_WALLET_MAPPER
                        .convertUserWalletEntityToUserWalletDto(
                                userWalletRepository.
                                        findByUserId(Long.parseLong(transferRequestDto
                                                .getSender()
                                                .getUserId())));

        UserWalletDto receiverWalletDto = UserWalletMapper.USER_WALLET_MAPPER
                .convertUserWalletEntityToUserWalletDto(
                        userWalletRepository.
                                findByUserId(Long.parseLong(transferRequestDto
                                        .getReceiver()
                                        .getUserId())));

        log.info(String.format("TransferFundsServiceImpl::transferFunds sender wallet %s",senderWalletDto.toString()));
        log.info(String.format("TransferFundsServiceImpl::transferFunds receiver wallet %s",receiverWalletDto.toString()));

        //2. Deduct the amount specified from the sender balance & Add the amount in receiver balance.
        senderWalletDto.setBalance
                (new BigDecimal(String.valueOf(new BigDecimal(senderWalletDto.getBalance()).subtract(new BigDecimal(transferRequestDto.getAmount())))).toString());
        receiverWalletDto.setBalance
                (new BigDecimal(String.valueOf(new BigDecimal(receiverWalletDto.getBalance()).add(new BigDecimal(transferRequestDto.getAmount())))).toString());

        log.info(String.format("TransferFundsServiceImpl::transferFunds sender wallet after transfer %s",senderWalletDto));
        log.info(String.format("TransferFundsServiceImpl::transferFunds receiver wallet after transfer %s",receiverWalletDto));

        //3. Save both the wallet objects in the DB.
        userWalletRepository.save(UserWalletMapper.USER_WALLET_MAPPER.convertUserWalletDtoToUserWalletEntity(senderWalletDto));
        log.info(String.format("TransferFundsServiceImpl::transferFunds saved sender wallet %s",senderWalletDto));

        userWalletRepository.save(UserWalletMapper.USER_WALLET_MAPPER.convertUserWalletDtoToUserWalletEntity(receiverWalletDto));
        log.info(String.format("TransferFundsServiceImpl::transferFunds saved receiver wallet %s",receiverWalletDto));

        //4. Log Transactions

        UsersDto senderUserDto = UserMapper.USER_MAPPER
                .convertUserEntityToUsersDto(
                        userRepository
                                .findById(Long.parseLong(transferRequestDto.getSender().getUserId()))
                                .orElseGet(UserEntity::new));

        UserWalletTransactionHistoryDto senderTransaction =
                new UserWalletTransactionHistoryDto();
        senderTransaction.setUserId(senderWalletDto.getUserId());
        senderTransaction.setTransactionType(AppConstants.WALLET_TRANSACTION_TYPE_DEBIT);
        senderTransaction.setUserType(senderUserDto.getUserType());
        senderTransaction.setAmount(transferRequestDto.getAmount());
        senderTransaction.setActionDate(LocalDateTime.now());

        UserWalletTransactionHistoryEntity senderWalletTransactionHistoryEntity=
                UserWalletTransactionHistoryEntity
                        .builder()
                        .userId(Long.parseLong(senderTransaction.getUserId()))
                        .amount(new BigDecimal(senderTransaction.getAmount()))
                        .userType(senderTransaction.getUserType())
                        .transactionId(transferRequestDto.getTransactionId())
                        .transactionType(AppConstants.WALLET_TRANSACTION_TYPE_CREDIT)
                        .build();

        userWalletTxnHstRepository.save(senderWalletTransactionHistoryEntity);
        log.info(String.format("TransferFundsServiceImpl::transferFunds Sender Transaction Logged : %s",senderWalletTransactionHistoryEntity));

        UsersDto receiverUserDto = UserMapper.USER_MAPPER
                .convertUserEntityToUsersDto(
                        userRepository
                                .findById(Long.parseLong(transferRequestDto.getReceiver().getUserId()))
                                .orElseGet(UserEntity::new));

        UserWalletTransactionHistoryDto receiverTransaction =
                new UserWalletTransactionHistoryDto();
        receiverTransaction.setUserId(receiverWalletDto.getUserId());
        receiverTransaction.setTransactionType(AppConstants.WALLET_TRANSACTION_TYPE_CREDIT);
        receiverTransaction.setUserType(receiverUserDto.getUserType());
        receiverTransaction.setAmount(transferRequestDto.getAmount());
        receiverTransaction.setActionDate(LocalDateTime.now());

        UserWalletTransactionHistoryEntity receiverWalletTransactionHistoryEntity=
                UserWalletTransactionHistoryEntity
                        .builder()
                        .userId(Long.parseLong(senderTransaction.getUserId()))
                        .amount(new BigDecimal(senderTransaction.getAmount()))
                        .userType(senderTransaction.getUserType())
                        .transactionId(transferRequestDto.getTransactionId())
                        .transactionType(AppConstants.WALLET_TRANSACTION_TYPE_CREDIT)
                        .build();

        userWalletTxnHstRepository.save(receiverWalletTransactionHistoryEntity);
        log.info(String.format("TransferFundsServiceImpl::transferFunds Receiver Transaction Logged : %s",receiverTransaction));
    }
}
