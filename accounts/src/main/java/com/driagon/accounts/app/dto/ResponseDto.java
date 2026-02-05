package com.driagon.accounts.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class ResponseDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -4972044846190217292L;

    private String statusCode;
    private String statusMsg;
}