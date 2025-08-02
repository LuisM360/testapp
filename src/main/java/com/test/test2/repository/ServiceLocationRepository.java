package com.test.test2.repository;

import com.test.test2.model.ServiceLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceLocationRepository extends JpaRepository<ServiceLocation, Long> {
}
