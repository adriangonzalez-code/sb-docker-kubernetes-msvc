package com.driagon.accounts.app.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class CustomerDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -8379655609995567125L;

    private String name;
    private String email;
    private String mobileNumber;
    private AccountsDto accountsDto;
}