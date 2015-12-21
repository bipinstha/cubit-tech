package com.alindus.iss.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alindus.iss.domain.Candidate;
import com.alindus.iss.service.CandidateService;


@RestController
@RequestMapping(value = "/secure/candidate", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
@ResponseStatus(HttpStatus.OK)
public class CandidateController {
	
	Logger logger = Logger.getLogger(CandidateController.class);

	@Autowired
	private CandidateService candidateService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(@Valid @RequestBody Candidate candidate, BindingResult result) {
		logger.error(candidate.getEmail());
		if(result.hasErrors()){
			logger.error(result.getAllErrors());
			throw new IllegalArgumentException(result.getAllErrors().toString());
		}
		try {
			candidateService.add(candidate);
		} catch (IllegalArgumentException ex) {
			this.logger.error(ex.getMessage());
			throw new IllegalArgumentException(ex.getMessage());
		}
		
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(@RequestBody Candidate candidate) {
		System.out.println("candidate in controller"+candidate);
		try {
			candidateService.update(candidate);
		} catch (IllegalArgumentException ex) {
			this.logger.error(ex.getMessage());
			throw new IllegalArgumentException(ex.getMessage());
		}
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public void remove(@RequestBody Long candidateId) {
		
		try {
			candidateService.remove(candidateId);
		} catch (IllegalArgumentException ex) {
			this.logger.error(ex.getMessage());
			throw new IllegalArgumentException(ex.getMessage());
		}
	}
	
	@RequestMapping(value = "/findone/{candidateId}", method = RequestMethod.GET)	
	@ResponseBody
	public Candidate findOne(@PathVariable Long candidateId) {
		
		try {
			return candidateService.findOne(candidateId);
		} catch (IllegalArgumentException ex) {
			this.logger.error(ex.getMessage());
			throw new IllegalArgumentException(ex.getMessage());
		}
	}
	
	@RequestMapping(value = "/findall", method = RequestMethod.GET)
	public List<Candidate> findAll(){
		
		try {
			return candidateService.findAll();
		} catch (IllegalArgumentException ex) {
			this.logger.error(ex.getMessage());
			throw new IllegalArgumentException(ex.getMessage());
		}
	}
	
	
	@RequestMapping(value = "/findbyssn", method = RequestMethod.POST)
	public Candidate findCandidateBySSN(@RequestBody Integer ssn) {
		
		try {
			return candidateService.findCandidateBySSN(ssn);
		} catch (IllegalArgumentException ex) {
			this.logger.error(ex.getMessage());
			throw new IllegalArgumentException(ex.getMessage());
		}
	}
	
	@RequestMapping(value = "/findbyemail", method = RequestMethod.POST)
	public Candidate findCandidateByEmail(@RequestBody String email) {
		
		try {
			return candidateService.findCandidateByEmail(email);
		} catch (IllegalArgumentException ex) {
			this.logger.error(ex.getMessage());
			throw new IllegalArgumentException(ex.getMessage());
		}
	}	
	
	@RequestMapping(value = "/findbylike", method = RequestMethod.POST)
	public List<Candidate> searchCandidateLike(@RequestBody String searchStr) {
		
		try {
			return candidateService.searchCandidateLike(searchStr);
		} catch (IllegalArgumentException ex) {
			this.logger.error(ex.getMessage());
			throw new IllegalArgumentException(ex.getMessage());
		}
		
	}
	
	@RequestMapping(value = "/{email}/remove", method = RequestMethod.POST)
	public void removeCandidate(@PathVariable String email) {
		
		try {
			candidateService.removeCandidate(email);
		} catch (IllegalArgumentException ex) {
			this.logger.error(ex.getMessage());
			throw new IllegalArgumentException(ex.getMessage());
		}
		
	}

	
}
