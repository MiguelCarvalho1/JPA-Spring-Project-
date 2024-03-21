package com.miguel.spring.infrastructure.repository;

import com.miguel.spring.infrastructure.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, String> {
}
