package com.meditrack.billingservice.repo;

import com.meditrack.billingservice.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {}
