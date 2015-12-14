package com.alindus.iss.service;

import java.util.List;

import com.alindus.iss.domain.Candidate;

public interface CandidateService extends AbstractService<Candidate, Long> {

	public Candidate findCandidateBySSN(Integer ssn);

	public Candidate findCandidateByEmail(String email);

	public List<Candidate> searchCandidateLike(String searchStr);
	
	public void removeCandidate(String email);

}
