package com.miguel.spring.infrastructure.repository;

import com.miguel.spring.infrastructure.models.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, String> {
}
