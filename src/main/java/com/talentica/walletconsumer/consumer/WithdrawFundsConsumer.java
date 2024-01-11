package com.talentica.walletconsumer.consumer;

import com.talentica.walletconsumer.constants.AppConstants;
import com.talentica.walletconsumer.dto.TransferRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WithdrawFundsConsumer {

    @KafkaListener(topics = {AppConstants.TOPIC_WITHDRAW_FUNDS},
            groupId = "payment-wallet-consumer-group")
    public void consumeWithdrawFundsRequest(){
        log.info(String.format("TransferFundsConsumer::consumeWithdrawFundsRequest -> " +
                        "Withdraw request received ==> "));
    }
}
