package com.miguel.spring.aplication.services;

import com.miguel.spring.aplication.dtos.RequestDTO;
import com.miguel.spring.aplication.services.exceptions.DatabaseException;
import com.miguel.spring.aplication.services.exceptions.ResourceNotFoundException;
import com.miguel.spring.infrastructure.models.Customer;
import com.miguel.spring.infrastructure.models.Pizza;
import com.miguel.spring.infrastructure.models.Request;
import com.miguel.spring.infrastructure.repository.RequestRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class RequestService {
    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private CustomerService customerService;

    public Request requestRegistration(RequestDTO requestDTO){
        Pizza pizza = pizzaService.findById(requestDTO.getPizzaId());
        Customer customer = customerService.findById(requestDTO.getTelephone());
        Request request = new Request(LocalDateTime.now(ZoneId.of("UTC")), requestDTO.getAmount(), requestDTO.getPrice(), pizza, customer);

        return requestRepository.save(request);
    }
    public Request findById(Long id) {
        Optional<Request> optional = requestRepository.findById(String.valueOf(id));
        return optional.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<Request> findAll() {
        return requestRepository.findAll();
    }

    public Request update(Long id, RequestDTO requestDTO) {
        try {
            Request entity = requestRepository.getReferenceById(String.valueOf(id));
            updateEntity(entity, requestDTO);
            return requestRepository.save(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateEntity(Request entity, RequestDTO request) {
        entity.setAmount(request.getAmount());
    }

    public void deleteById(Long id) {
        try {
            requestRepository.deleteById(String.valueOf(id));
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

}
