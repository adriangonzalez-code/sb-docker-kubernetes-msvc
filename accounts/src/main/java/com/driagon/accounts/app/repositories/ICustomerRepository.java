package com.driagon.accounts.app.repositories;

import com.driagon.accounts.app.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findCustomerByMobileNumber(String mobileNumber);
}