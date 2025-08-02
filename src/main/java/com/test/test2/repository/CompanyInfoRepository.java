package com.test.test2.repository;

import com.test.test2.model.CompanyInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyInfoRepository extends JpaRepository<CompanyInfo,Integer> {

}
