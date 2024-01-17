package com.talentica.walletconsumer.service;

import com.stripe.Stripe;
//import com.stripe.exception.StripeException;
//import com.stripe.model.Charge;
import com.talentica.walletconsumer.dto.StripeChargeDto;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

//import java.util.HashMap;
//import java.util.Map;

@Service
@Slf4j
public class StripeServiceImpl implements StripeService{

    @Value("${api.stripe.key}")
    private String apiKey;

    @PostConstruct
    public void init(){
        Stripe.apiKey = apiKey;
    }

    public StripeChargeDto charge(StripeChargeDto chargeRequest){
        try {
            /*Map<String, Object> metaData = new HashMap<>();
            metaData.put("id", chargeRequest.getChargeId());
            metaData.putAll(chargeRequest.getAdditionalInfo());

            Map<String, Object> addFundsChargeParams =
                    new HashMap<>();
            addFundsChargeParams.put("amount",
                    (Integer.parseInt(chargeRequest.getAmount()) * 100));
            addFundsChargeParams.put("currency",
                    "USD");
            addFundsChargeParams.put("description",
                    "Payment for ID " + chargeRequest.getAdditionalInfo().getOrDefault("ID_TAG", ""));
            addFundsChargeParams.put("source",
                    chargeRequest.getStripeToken());
            addFundsChargeParams.put("metadata",
                    metaData);
            Charge charge = Charge.create(addFundsChargeParams);
            chargeRequest.setMessage(charge
                    .getOutcome()
                    .getSellerMessage());

            if(charge.getPaid()){
                log.info(String.format("StripeServiceImpl::charge -> Charge successful -> %s",charge.toString()));
                chargeRequest.setChargeId(chargeRequest.getChargeId());
                chargeRequest.setSuccess(true);
            }*/
            chargeRequest.setSuccess(true);
            return chargeRequest;
        }catch (Exception e){
            log.error("StripeServiceImpl::charge -> ",e);
            throw new RuntimeException(e.getMessage());
        }
        /*catch (StripeException e){
            log.error("StripeServiceImpl::charge -> ",e);
            throw new RuntimeException(e.getMessage());
        }*/
    }
}
