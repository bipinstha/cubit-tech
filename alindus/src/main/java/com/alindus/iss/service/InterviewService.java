package com.alindus.iss.service;

import java.util.List;

import com.alindus.iss.domain.Client;
import com.alindus.iss.domain.Interview;
import com.alindus.iss.domain.InterviewType;
import com.alindus.iss.domain.Round;
import com.alindus.iss.domain.Technology;
import com.alindus.iss.domain.Vendor;

public interface InterviewService extends AbstractService<Interview, Long>{
	
	public List<Client> findClientsByNameLike(String name);
	
	public List<Vendor> findVendorsByNameLike(String name);
	
	public List<Technology> findTechnologiesByNameLike(String name);
	
	public List<Round> findRoundsByNameLike(String name);
	
	public List<InterviewType> findInterviewsTypeByTypeLike(String type);

}
