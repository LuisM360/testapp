package com.test.test2.repository;

import com.test.test2.model.MonthlyBillingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthlyBillingStatusRepository extends JpaRepository<MonthlyBillingStatus, Long> {
}
