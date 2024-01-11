package com.talentica.walletconsumer.dto;

import lombok.Data;

@Data
public class TransferRequestDto {

        private String amount;
        private UsersDto sender;
        private UsersDto receiver;
}
