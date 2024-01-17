package com.talentica.walletconsumer.consumer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.talentica.walletconsumer.constants.AppConstants;
import com.talentica.walletconsumer.dto.TransferRequestDto;
import com.talentica.walletconsumer.service.TransferFundsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Service;

import java.io.DataInput;
import java.io.IOException;

@Service
@Slf4j
public class TransferFundsConsumer {

    @Autowired
    @Qualifier("transferFundsServiceImpl")
    private TransferFundsServiceImpl transferFundsService;

    @KafkaListener(topics = {AppConstants.TOPIC_TRANSFER_FUNDS},
            groupId = "payment-wallet-consumer-group")
    public void consumeTransferFundsRequest(ConsumerRecord<String, String> consumerRecord){
        try {
            log.info(String.format("TransferFundsConsumer::consumeTransferFundsRequest -> " +
                            "Transfer request received ==> %s",
                    consumerRecord.value()));
            JSONObject jsonObject = new JSONObject(consumerRecord.value());
            ObjectMapper objectMapper = new ObjectMapper();
            TransferRequestDto transferRequestDto = objectMapper
                    .readValue(jsonObject.getString("payload"),
                            TransferRequestDto.class);
            transferFundsService.transferFunds(transferRequestDto);
        }catch (JsonProcessingException | JSONException e){
            log.error(String.format("Unable to deserialize message ==> %s",consumerRecord.value()));
        }
    }
}
