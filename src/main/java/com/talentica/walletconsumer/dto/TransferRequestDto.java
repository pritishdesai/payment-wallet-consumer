package com.talentica.walletconsumer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequestDto {

        private String amount;
        private UsersDto sender;
        private UsersDto receiver;
}
