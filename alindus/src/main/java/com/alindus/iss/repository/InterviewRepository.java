package com.alindus.iss.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alindus.iss.domain.Client;
import com.alindus.iss.domain.Interview;
import com.alindus.iss.domain.InterviewType;
import com.alindus.iss.domain.Round;
import com.alindus.iss.domain.Technology;
import com.alindus.iss.domain.Vendor;

public interface InterviewRepository extends JpaRepository<Interview, Long>{

	@Query(value = "delete from InterviewRound ir where ir.id=?1")
	public void removeInterviewRound(Long id);
	
	@Query(value = "select c from Client c where c.name=?1")
	public Client findClientByName(String name);
	
	@Query(value = "select c from Client c where c.name like %?1%")
	public List<Client> findClientsByNameLike(String name);
	
	@Query(value = "select v from Vendor v where v.name=?1")
	public Vendor findVendorByName(String name);
	
	@Query(value = "select v from Vendor v where v.name like %?1%")
	public List<Vendor> findVendorsByNameLike(String name);
	
	@Query(value = "select t from Technology t where t.name=?1")
	public Technology findTechnologyByName(String name);
	
	@Query(value = "select t from Technology t where t.name like %?1%")
	public List<Technology> findTechnologiesByNameLike(String name);
	
	@Query(value = "select r from Round r where r.name=?1")
	public Round findRoundByName(String name);
	
	@Query(value = "select r from Round r where r.name like %?1%")
	public List<Round> findRoundByNameLike(String name);
	
	@Query(value = "select it from InterviewType it where it.type=?1")
	public InterviewType findInterviewTypeByType(String type);
	
	@Query(value = "select it from InterviewType it where it.type like %?1%")
	public List<InterviewType> findInterviewsTypeByTypeLike(String type);
	
}