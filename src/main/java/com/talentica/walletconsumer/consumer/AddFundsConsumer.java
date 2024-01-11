package com.talentica.walletconsumer.consumer;

import com.talentica.walletconsumer.constants.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AddFundsConsumer {

    @KafkaListener(topics = {AppConstants.TOPIC_ADD_FUNDS},
            groupId = "payment-wallet-consumer-group")
    public void consumeAddFundsRequest(){
        log.info(String.format("TransferFundsConsumer::consumeAddFundsRequest -> " +
                "Add request received ==> "));
    }
}
