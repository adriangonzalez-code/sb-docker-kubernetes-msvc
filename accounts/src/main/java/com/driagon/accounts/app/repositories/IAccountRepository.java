package com.driagon.accounts.app.repositories;

import com.driagon.accounts.app.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findAccountsByCustomerId(Long customerId);

    Optional<Account> findAccountsByAccountNumber(Long accountNumber);
}