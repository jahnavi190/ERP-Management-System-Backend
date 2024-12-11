package com.example.ERP_MANAGEMENT.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ERP_MANAGEMENT.models.Bus;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
}
