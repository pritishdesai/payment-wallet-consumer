package com.talentica.walletconsumer.consumer;

import com.talentica.walletconsumer.constants.AppConstants;
import com.talentica.walletconsumer.dto.TransferRequestDto;
import com.talentica.walletconsumer.service.TransferFundsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransferFundsConsumer {

    @Autowired
    @Qualifier("transferFundsServiceImpl")
    private TransferFundsServiceImpl transferFundsService;

    @KafkaListener(topics = {AppConstants.TOPIC_TRANSFER_FUNDS},
            groupId = "payment-wallet-consumer-group")
    public void consumeTransferFundsRequest(TransferRequestDto transferRequestDto){
        log.info(String.format("TransferFundsConsumer::consumeTransferFundsRequest -> " +
                        "Transfer request received ==> %s",
                transferRequestDto.toString()));
        transferFundsService.transferFunds(transferRequestDto);





    }
}
