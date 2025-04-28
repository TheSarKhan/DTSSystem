package com.sarkhan.dtssystem.repository.company;

import com.sarkhan.dtssystem.model.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {
}
