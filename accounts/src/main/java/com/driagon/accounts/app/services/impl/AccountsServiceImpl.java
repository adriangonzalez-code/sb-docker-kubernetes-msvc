package com.driagon.accounts.app.services.impl;

import com.driagon.accounts.app.constants.AccountsConstants;
import com.driagon.accounts.app.dto.AccountsDto;
import com.driagon.accounts.app.dto.CustomerDto;
import com.driagon.accounts.app.entities.Account;
import com.driagon.accounts.app.entities.Customer;
import com.driagon.accounts.app.exceptions.CustomerAlreadyExistsException;
import com.driagon.accounts.app.exceptions.ResourceNotFoundException;
import com.driagon.accounts.app.mappers.AccountsMapper;
import com.driagon.accounts.app.mappers.CustomersMapper;
import com.driagon.accounts.app.repositories.IAccountRepository;
import com.driagon.accounts.app.repositories.ICustomerRepository;
import com.driagon.accounts.app.services.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private IAccountRepository accountRepository;
    private ICustomerRepository customerRepository;

    @Override
    @Transactional
    public void createAccount(CustomerDto request) {
        Optional<Customer> optionalCustomer =
                this.customerRepository.findCustomerByMobileNumber(request.getMobileNumber());

        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException(
                    "Customer with mobile number " + request.getMobileNumber() + " already exists"
            );
        }

        Customer customer = CustomersMapper.mapToCustomer(request, new Customer());
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Admin");

        Customer savedCustomer = this.customerRepository.save(customer);

        Account newAccount = this.createNewAccount(savedCustomer);
        this.accountRepository.save(newAccount);
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = this.customerRepository.findCustomerByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        Account account = this.accountRepository.findAccountsByCustomerId(customer.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));
        CustomerDto customerDto = CustomersMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(account, new AccountsDto()));

        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto request) {
        boolean isUpdated = false;

        AccountsDto accountsDto = request.getAccountsDto();

        if (accountsDto != null) {
            Account account = this.accountRepository.findById(accountsDto.getAccountNumber()).orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", accountsDto.getAccountNumber().toString()));

            AccountsMapper.mapToAccount(accountsDto, account);
            account = this.accountRepository.save(account);

            Long customerId = account.getCustomerId();
            Customer customer = this.customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", customerId.toString()));
            CustomersMapper.mapToCustomer(request, customer);
            customer = this.customerRepository.save(customer);

            isUpdated = true;
        }

        return isUpdated;
    }

    private Account createNewAccount(Customer customer) {
        Account newAccount = new Account();
        newAccount.setCustomerId(customer.getCustomerId());
        newAccount.setAccountNumber((long) Math.floor(Math.random() * 10000000L));
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Admin");
        return newAccount;
    }
}