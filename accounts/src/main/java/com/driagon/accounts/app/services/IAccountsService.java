package com.driagon.accounts.app.services;

import com.driagon.accounts.app.dto.CustomerDto;

public interface IAccountsService {

    /**
     * Create an account
     * @param request CustomerDto
     */
    void createAccount(CustomerDto request);

    /**
     * Get an account
     * @param mobileNumber String
     * @return Account
     */
    CustomerDto fetchAccount(String mobileNumber);

    /**
     * Update an account
     * @param request CustomerDto
     * @return boolean
     */
    boolean updateAccount(CustomerDto request);
}