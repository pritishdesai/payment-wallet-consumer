package com.talentica.walletconsumer.consumer;

import com.talentica.walletconsumer.constants.AppConstants;
import com.talentica.walletconsumer.dto.TransferRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransferFundsConsumer {

    @KafkaListener(topics = {AppConstants.TOPIC_TRANSFER_FUNDS},
            groupId = "payment-wallet-consumer-group")
    public void consumeTransferFundsRequest(TransferRequestDto transferRequestDto){
        log.info(String.format("TransferFundsConsumer::consumeTransferFundsRequest -> " +
                        "Transfer request received ==> %s",
                transferRequestDto.toString()));




    }
}
