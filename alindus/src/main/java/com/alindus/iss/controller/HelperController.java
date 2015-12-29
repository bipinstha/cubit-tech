package com.alindus.iss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alindus.iss.domain.Client;
import com.alindus.iss.domain.InterviewRound.InterviewStatus;
import com.alindus.iss.domain.InterviewType;
import com.alindus.iss.domain.Round;
import com.alindus.iss.domain.Technology;
import com.alindus.iss.domain.Vendor;
import com.alindus.iss.service.InterviewService;

@RestController
@RequestMapping(value = "/secure", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
		MediaType.APPLICATION_JSON_VALUE })
@ResponseStatus(HttpStatus.OK)
public class HelperController {

	@Autowired
	private InterviewService interviewService;
	
	@RequestMapping(value = "/client/all", method = RequestMethod.GET)
	public List<Client> findAllClients() {
		try {
			return interviewService.findAllClients();
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

	@RequestMapping(value = "/vendor/all", method = RequestMethod.GET)
	public List<Vendor> findAllVendor() {
		try {
			return interviewService.findAllVendors();
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

	
	@RequestMapping(value = "/technologie/all", method = RequestMethod.GET)
	public List<Technology> findAllTechnologies() {
		try {
			return interviewService.findAllTechnologies();
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

	@RequestMapping(value = "/round/all", method = RequestMethod.GET)
	public List<Round> findAllRounds() {
		try {
			return interviewService.findAllRounds();
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

	@RequestMapping(value = "/interviewtype/all", method = RequestMethod.GET)
	public List<InterviewType> findAllInterviewTypes() {
		try {
			return interviewService.findAllInterviewTypes();
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
	
	@RequestMapping(value = "/all/status", method = RequestMethod.GET)
	public InterviewStatus[] getAllInterviewStatus() {
		return InterviewStatus.values();
	}
}
