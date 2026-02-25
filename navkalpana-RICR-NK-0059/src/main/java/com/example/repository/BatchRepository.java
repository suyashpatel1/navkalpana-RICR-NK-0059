package com.example.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Batch;
import com.example.model.BatchStatus;

public interface BatchRepository extends JpaRepository<Batch, Long> {
    List<Batch> findByStatus(BatchStatus status);
    
}
