package com.alindus.iss.service;

import java.util.List;

import com.alindus.iss.domain.Client;
import com.alindus.iss.domain.Interview;
import com.alindus.iss.domain.InterviewRound;
import com.alindus.iss.domain.InterviewType;
import com.alindus.iss.domain.Round;
import com.alindus.iss.domain.Technology;
import com.alindus.iss.domain.Vendor;

public interface InterviewService extends AbstractService<Interview, Long> {

	public List<Client> findAllClients();

	public List<Client> findClientsByNameLike(String name);

	public List<Vendor> findAllVendors();

	public List<Vendor> findVendorsByNameLike(String name);

	public List<Technology> findAllTechnologies();

	public List<Technology> findTechnologiesByNameLike(String name);

	public List<Round> findAllRounds();

	public List<Round> findRoundsByNameLike(String name);

	public List<InterviewType> findAllInterviewTypes();

	public List<InterviewType> findInterviewsTypeByTypeLike(String type);

	public void updateInterviewRound(InterviewRound interviewRound);

	public List<InterviewRound> findInterviewRounds();

	public List<InterviewRound> findInterviewRoundsByInterviewId(Long id);

}
