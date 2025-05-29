package com.sarkhan.dtssystem.repository.content.eligibility;

import com.sarkhan.dtssystem.model.content.eligibility.data.Criteria;
import com.sarkhan.dtssystem.model.content.home.data.HeaderCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CriteriaRepository extends JpaRepository<Criteria,Long> {
}
