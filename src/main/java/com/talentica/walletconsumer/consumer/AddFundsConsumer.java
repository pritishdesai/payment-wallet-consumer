package com.talentica.walletconsumer.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.talentica.walletconsumer.constants.AppConstants;
import com.talentica.walletconsumer.dto.AddWithdrawFundsDto;
import com.talentica.walletconsumer.service.AddFundsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AddFundsConsumer {

    private final AddFundsServiceImpl addFundsService;

    @KafkaListener(topics = {AppConstants.TOPIC_ADD_FUNDS},
            groupId = "payment-wallet-consumer-group")
    public void consumeAddFundsRequest(ConsumerRecord<String, String> consumerRecord){
        try{
            log.info(String.format("AddFundsConsumer::consumeAddFundsRequest -> " +
                    "Add request received ==> %s",consumerRecord.value()));
            JSONObject jsonObject = new JSONObject(consumerRecord.value());
            ObjectMapper objectMapper = new ObjectMapper();
            AddWithdrawFundsDto addWithdrawFundsDto =
                    objectMapper
                            .readValue(jsonObject.getString("payload"),
                                    AddWithdrawFundsDto.class);
            addFundsService.addFunds(addWithdrawFundsDto);
        }catch (JSONException | JsonProcessingException e){
            log.error(String.format("AddFundsConsumer::consumeAddFundsRequest ==> Unable to deserialize message ==> %s",consumerRecord.value()));
        }
    }
}
