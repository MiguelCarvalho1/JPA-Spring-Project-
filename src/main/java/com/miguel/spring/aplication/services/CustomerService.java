package com.miguel.spring.aplication.services;

import com.miguel.spring.aplication.services.exceptions.DatabaseException;
import com.miguel.spring.aplication.services.exceptions.ResourceNotFoundException;
import com.miguel.spring.infrastructure.models.Customer;
import com.miguel.spring.infrastructure.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer customerRegistration(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer findById(String id) {
        Optional<Customer> optional = customerRepository.findById(id);
        return optional.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer update(String id, Customer customer) {
        try {
            Customer entity = customerRepository.getReferenceById(id);
            updateEntity(entity, customer);
            return customerRepository.save(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateEntity(Customer entity, Customer customer) {
        entity.setAddress(customer.getAddress());
        entity.setName(customer.getName());
    }

    public void deleteById(String id) {
        try {
            customerRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
