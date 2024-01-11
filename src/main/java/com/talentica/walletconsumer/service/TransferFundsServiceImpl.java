package com.talentica.walletconsumer.service;

import com.talentica.walletconsumer.dto.TransferRequestDto;
import org.springframework.stereotype.Service;

@Service
public class TransferFundsServiceImpl implements TransferFundsService{
    @Override
    public void transferFunds(TransferRequestDto transferRequestDto) {
        //1.Get Balance for both sender and receiver



        //2. Deduct the amount specified from the sender balance & Add the amount in receiver balance.
        //3. Save both the wallet objects in the DB.





    }
}
