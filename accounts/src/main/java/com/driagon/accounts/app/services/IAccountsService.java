package com.driagon.accounts.app.services;

import com.driagon.accounts.app.dto.CustomerDto;

public interface IAccountsService {

    /**
     * Create an account
     * @param request CustomerDto
     */
    void createAccount(CustomerDto request);
}