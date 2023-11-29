package com.techforb.unicomer.entitites.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferResponse {
    private Integer id;
    private String transferNumber;
    private String accountNumberDestiny;
    private String accountNumberOrigin;
}
