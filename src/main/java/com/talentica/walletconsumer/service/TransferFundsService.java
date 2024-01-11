package com.talentica.walletconsumer.service;

import com.talentica.walletconsumer.dto.TransferRequestDto;

public interface TransferFundsService {
    public void transferFunds(TransferRequestDto transferRequestDto);
}
