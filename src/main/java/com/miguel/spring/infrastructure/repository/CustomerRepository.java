package com.miguel.spring.infrastructure.repository;

import com.miguel.spring.infrastructure.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
