package com.miguel.spring.aplication.services;

import com.miguel.spring.aplication.services.exceptions.DatabaseException;
import com.miguel.spring.aplication.services.exceptions.ResourceNotFoundException;
import com.miguel.spring.infrastructure.models.Pizza;
import com.miguel.spring.infrastructure.repository.PizzaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {
    @Autowired
    private PizzaRepository pizzaRepository;

    public Pizza pizzaRegistration (Pizza pizza){
        return pizzaRepository.save(pizza);
    }

    public Pizza findById(Long id){
        Optional<Pizza> optional = pizzaRepository.findById(String.valueOf(id));
        return optional.orElseThrow(()-> new ResourceNotFoundException(id));
    }

    public List<Pizza> findAll() {
        return pizzaRepository.findAll();
    }

    public Pizza update (Long id, Pizza pizza){
        try{
            Pizza entity = pizzaRepository.getReferenceById(String.valueOf(id));
            updateEntity(entity, pizza);
            return pizzaRepository.save(entity);

        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateEntity(Pizza entity, Pizza pizza) {
        entity.setPrice(pizza.getPrice());
        entity.setName(pizza.getName());
    }

    public void deleteById(Long id){
        try{
            pizzaRepository.deleteById(String.valueOf(id));

        }catch(EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }
}
