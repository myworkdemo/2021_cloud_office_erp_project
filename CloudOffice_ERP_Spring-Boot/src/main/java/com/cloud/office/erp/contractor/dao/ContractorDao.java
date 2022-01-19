package com.cloud.office.erp.contractor.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.cloud.office.erp.entity.Contractor;

@Repository
public interface ContractorDao extends JpaRepository<Contractor, Long>{

	@Query("FROM Contractor c WHERE c.contractorName = :contractorName GROUP BY c.contractorName")
	public Optional<Contractor> getContractorByName(String contractorName);
}
