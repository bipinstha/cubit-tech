package com.alindus.iss.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
@RequestMapping(value = "/secure/candidate", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
		MediaType.APPLICATION_JSON_VALUE })
@ResponseStatus(HttpStatus.OK)
public class CandidateController {

	Logger logger = Logger.getLogger(CandidateController.class);

	@Autowired
	private CandidateService candidateService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Candidate add(@Valid @RequestBody Candidate candidate, BindingResult bindingResult) {
		logger.error(candidate.getEmail());

		if (bindingResult.hasErrors()) {
			List<String> errorLsit = new ArrayList<String>();
			for (Object object : bindingResult.getAllErrors()) {
				if (object instanceof FieldError) {
					FieldError fieldError = (FieldError) object;
					errorLsit.add(fieldError.getDefaultMessage());
				}

			}
			throw new IllegalArgumentException(errorLsit.toString());
		} else {

			try {
				candidateService.add(candidate);
				return candidate;
			} catch (IllegalArgumentException ex) {
				this.logger.error(ex.getMessage());
				throw new IllegalArgumentException(ex.getMessage());
			}
		}

	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Candidate update(@RequestBody Candidate candidate) {
		try {
			candidateService.update(candidate);
			return candidate;
		} catch (IllegalArgumentException ex) {
			this.logger.error(ex.getMessage());
			throw new IllegalArgumentException(ex.getMessage());
		}
	}

	@RequestMapping(value = "/{id}/remove", method = RequestMethod.GET)
	public void remove(@PathVariable Long id) {
		try {
			candidateService.remove(id);
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
	public List<Candidate> findAll() {
		try {
			return candidateService.findAll();
		} catch (IllegalArgumentException ex) {
			this.logger.error(ex.getMessage());
			throw new IllegalArgumentException(ex.getMessage());
		}
	}

	@RequestMapping(value = "/{ssn}/findbyssn", method = RequestMethod.GET)
	public Candidate findCandidateBySSN(@PathVariable String ssn) {
		try {
			return candidateService.findCandidateBySSN(ssn);
		} catch (IllegalArgumentException ex) {
			this.logger.error(ex.getMessage());
			throw new IllegalArgumentException(ex.getMessage());
		}
	}

	@RequestMapping(value = "/{email}/find", method = RequestMethod.GET)
	public Candidate findCandidateByEmail(@PathVariable String email) {
		try {
			return candidateService.findCandidateByEmail(email);
		} catch (IllegalArgumentException ex) {
			this.logger.error(ex.getMessage());
			throw new IllegalArgumentException(ex.getMessage());
		}
	}

	@RequestMapping(value = "/{searchStr}/findbylike", method = RequestMethod.GET)
	public List<Candidate> searchCandidateLike(@PathVariable String searchStr) {
		try {
			return candidateService.searchCandidateLike(searchStr);
		} catch (IllegalArgumentException ex) {
			this.logger.error(ex.getMessage());
			throw new IllegalArgumentException(ex.getMessage());
		}
	}

	@RequestMapping(value = "/{email}/remove", method = RequestMethod.DELETE)
	public void removeCandidate(@PathVariable String email) {
		try {
			candidateService.removeCandidate(email);
		} catch (IllegalArgumentException ex) {
			this.logger.error(ex.getMessage());
			throw new IllegalArgumentException(ex.getMessage());
		}
	}

}
