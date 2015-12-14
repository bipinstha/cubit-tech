package com.alindus.iss.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alindus.iss.domain.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

	@Query("select c from Candidate c inner join c.ssn s where s.lastValue = ?1")
	public Candidate findBySsn(Integer ssn);
	
	public Candidate findByEmail(String email);
	
	@Query("select c from Candidate c where c.firstName like %?1% or c.middleName like %?1% or c.lastName like %?1% or c.email like %?1%")
	public List<Candidate> searchCandidateLike(String searchStr);
}
