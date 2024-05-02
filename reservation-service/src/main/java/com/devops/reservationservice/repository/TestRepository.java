package com.devops.reservationservice.repository;

import com.devops.reservationservice.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Admin, Long> {
}
