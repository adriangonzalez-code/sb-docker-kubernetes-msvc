package com.driagon.accounts.app.controllers;

import com.driagon.accounts.app.constants.AccountsConstants;
import com.driagon.accounts.app.dto.CustomerDto;
import com.driagon.accounts.app.dto.ResponseDto;
import com.driagon.accounts.app.entities.Account;
import com.driagon.accounts.app.services.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AccountsController {

    private IAccountsService accountsService;

    @PostMapping(value = "/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto request) {
        this.accountsService.createAccount(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomerDto> getAccount(@RequestParam("mobileNumber") String mobileNumber) {
        CustomerDto response = this.accountsService.fetchAccount(mobileNumber);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}