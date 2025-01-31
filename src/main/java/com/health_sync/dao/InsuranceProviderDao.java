package com.health_sync.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.health_sync.pojos.Doctor;
import com.health_sync.pojos.InsuranceProvider;
import com.health_sync.pojos.Patient;

public interface InsuranceProviderDao extends JpaRepository<InsuranceProvider, Long> {
	Optional<InsuranceProvider> findByEmailAndPassword(String em, String pass);

	Optional<InsuranceProvider> findByEmail(String email);

	boolean existsByEmail(String email);

	@Query("select d from InsuranceProvider d join fetch d.insurancePlans where d.id=:id")
	InsuranceProvider getInsuranPlans(Long id);

	@Query("select p from InsuranceProvider p join fetch p.insuranceRequests where p.id=:providerId")
	InsuranceProvider getInsuranceRequests(Long providerId);
}
