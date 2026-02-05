package com.driagon.accounts.app.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class AccountsDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 6761691594360665366L;

    private Long accountNumber;
    private String accountType;
    private String branchAddress;
}