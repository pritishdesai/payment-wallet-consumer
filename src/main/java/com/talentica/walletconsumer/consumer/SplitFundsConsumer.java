package com.talentica.walletconsumer.consumer;

import com.talentica.walletconsumer.constants.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SplitFundsConsumer {
    @KafkaListener(topics = {AppConstants.TOPIC_SPLIT_FUNDS},
            groupId = "payment-wallet-consumer-group")
    public void consumeSplitFundsRequest(){
        log.info(String.format("TransferFundsConsumer::consumeSplitFundsRequest -> " +
                "Split request received ==> "));
    }
}
