package com.alindus.iss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alindus.iss.domain.Client;
import com.alindus.iss.domain.Interview;
import com.alindus.iss.domain.InterviewRound;
import com.alindus.iss.domain.InterviewType;
import com.alindus.iss.domain.Round;
import com.alindus.iss.domain.Technology;
import com.alindus.iss.domain.Vendor;
import com.alindus.iss.service.InterviewService;

@RestController
@RequestMapping(value = "/secure/interview", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
		MediaType.APPLICATION_JSON_VALUE })
@ResponseStatus(HttpStatus.OK)
public class InterviewController {

	@Autowired
	private InterviewService interviewService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Interview addInterview(@RequestBody Interview interview) {
		try {
			this.interviewService.add(interview);
			return interview;
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException(ex.getMessage());
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Interview updateInterview(@RequestBody Interview interview) {
		try {
			this.interviewService.update(interview);
			return interview;
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException(ex.getMessage());
		}
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Interview> findAllInterview() {
		try {
			return this.interviewService.findAll();
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException(ex.getMessage());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Interview findOneInterview(@PathVariable Long id) {
		try {
			return this.interviewService.findOne(id);
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException(ex.getMessage());
		}
	}

	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	public void removeInterview(@PathVariable Long id) {
		try {
			this.interviewService.remove(id);
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException(ex.getMessage());
		}
	}

	@RequestMapping(value = "/client/like/{name}", method = RequestMethod.GET)
	public List<Client> findClientLike(@PathVariable String name) {
		try {
			return interviewService.findClientsByNameLike(name);
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException(ex.getMessage());
		}
	}

	@RequestMapping(value = "/vendor/like/{name}", method = RequestMethod.GET)
	public List<Vendor> findVendorLike(@PathVariable String name) {
		try {
			return interviewService.findVendorsByNameLike(name);
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException(ex.getMessage());
		}
	}

	@RequestMapping(value = "/technology/like/{name}", method = RequestMethod.GET)
	public List<Technology> findTechnologiesLike(@PathVariable String name) {
		try {
			return interviewService.findTechnologiesByNameLike(name);
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException(ex.getMessage());
		}
	}

	@RequestMapping(value = "/round/like/{name}", method = RequestMethod.GET)
	public List<Round> findRoundsLike(@PathVariable String name) {
		try {
			return interviewService.findRoundsByNameLike(name);
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException(ex.getMessage());
		}
	}

	@RequestMapping(value = "/interviewtype/like/{name}", method = RequestMethod.GET)
	public List<InterviewType> findInterviewTypeLike(@PathVariable String type) {
		try {
			return interviewService.findInterviewsTypeByTypeLike(type);
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException(ex.getMessage());
		}
	}

	@RequestMapping(value = "/update/interviewround", method = RequestMethod.POST)
	public InterviewRound updateInterviewRound(@RequestBody InterviewRound interviewRound) {
		try {
			this.interviewService.updateInterviewRound(interviewRound);
			return interviewRound;
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException(ex.getMessage());
		}
	}

	@RequestMapping(value="/ir/all", method=RequestMethod.GET)
	public List<InterviewRound> getInterviewRounds() {
		try {
			return this.interviewService.getInterviewRounds();
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException(ex.getMessage());
		}
	}
	
	@RequestMapping(value="/ir/all/{id}", method=RequestMethod.GET)
	public List<InterviewRound> getInterviewRoundsByInterviewId(@PathVariable Long id) {
		try {
			return this.interviewService.getInterviewRoundsByInterviewId(id);
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException(ex.getMessage());
		}
	}
	
}
