package com.driagon.accounts.app.services.impl;

import com.driagon.accounts.app.constants.AccountsConstants;
import com.driagon.accounts.app.dto.CustomerDto;
import com.driagon.accounts.app.entities.Account;
import com.driagon.accounts.app.entities.Customer;
import com.driagon.accounts.app.exceptions.CustomerAlreadyExistsException;
import com.driagon.accounts.app.mappers.CustomersMapper;
import com.driagon.accounts.app.repositories.IAccountRepository;
import com.driagon.accounts.app.repositories.ICustomerRepository;
import com.driagon.accounts.app.services.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private IAccountRepository accountRepository;
    private ICustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto request) {
        Optional<Customer> optionalCustomer = this.customerRepository.findCustomerByMobileNumber(request.getMobileNumber());

        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer with mobile number " + request.getMobileNumber() + " already exists");
        }

        Customer customer = CustomersMapper.mapToCustomer(request, new Customer());
        Customer savedCustomer = this.customerRepository.save(customer);
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Admin");
        this.accountRepository.save(this.createNewAccount(savedCustomer));

    }

    private Account createNewAccount(Customer customer) {
        Account newAccount = new Account();
        newAccount.setCustomerId(customer.getCustomerId());
        newAccount.setAccountNumber((long) Math.floor(Math.random() * 10000000L));
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }
}