package com.alindus.iss.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alindus.iss.domain.Address;
import com.alindus.iss.domain.Candidate;
import com.alindus.iss.domain.Phone;
import com.alindus.iss.domain.SocialSecurityNumber;
import com.alindus.iss.repository.CandidateRepository;
import com.alindus.iss.service.CandidateService;

@Service
public class CandidateServiceImpl implements CandidateService {

	@Autowired
	private CandidateRepository candidateRepository;

	@Override
	@Transactional
	public void add(Candidate t) {
		if (t.getId() != null) {
			throw new IllegalArgumentException("Invalid candidate.");
		}
		if (this.candidateRepository.findByEmail(t.getEmail()) != null) {
			throw new IllegalArgumentException("Candidate email already exist.");
		}
		if (this.candidateRepository.findBySsn(t.getSsn().getLastValue()) != null) {
			throw new IllegalArgumentException("Candidate ssn already exist.");
		}
		this.candidateRepository.save(t);

	}

	@Override
	@Transactional
	public void update(Candidate t) {
		if (t.getId() == null) {
			throw new IllegalArgumentException("Invalid candidate.");
		}
		Candidate c = this.candidateRepository.findByEmail(t.getEmail());
		if (c == null) {
			throw new IllegalArgumentException("Candidate email already exist.");
		}
		Address address = new Address(t.getAddress().getAddress(), t.getAddress().getCity(), t.getAddress().getState(),
				t.getAddress().getZipCode());
		address.setAddress1(t.getAddress().getAddress1());
		if (c.getAddress() != null)
			address.setId(c.getAddress().getId());
		Phone phone = new Phone(t.getPhone().getAreaCode(), t.getPhone().getPrefixValue(), t.getPhone().getNumber());
		if (c.getPhone() != null)
			phone.setId(c.getPhone().getId());
		Phone phone1 = new Phone(t.getPhone1().getAreaCode(), t.getPhone1().getPrefixValue(),
				t.getPhone1().getNumber());
		if (c.getPhone1() != null)
			phone1.setId(c.getPhone1().getId());
		SocialSecurityNumber ssn = new SocialSecurityNumber(t.getSsn().getInitValue(), t.getSsn().getMidValue(),
				t.getSsn().getLastValue());
		ssn.setId(c.getSsn().getId());

		Candidate can = new Candidate(t.getFirstName(), t.getLastName(), t.getEmail(), address, phone, ssn,
				t.getSkypeId());
		can.setId(c.getId());
		can.setMiddleName(t.getMiddleName());
		can.setEmail1(t.getEmail1());
		can.setPhone(phone1);
		can.setSkypeId1(t.getSkypeId1());
		this.candidateRepository.save(can);
	}

	@Override
	@Transactional
	public void remove(Long obj) {
		// TODO Auto-generated method stub
		if (obj == null) {
			throw new IllegalArgumentException("Invalid id");
		}
		this.candidateRepository.delete(obj);
	}

	@Override
	public Candidate findOne(Long obj) {
		if (obj == null) {
			throw new IllegalArgumentException("Invalid id.");
		}
		return this.candidateRepository.findOne(obj);
	}

	@Override
	public List<Candidate> findAll() {
		return this.candidateRepository.findAll();
	}

	@Override
	public Candidate findCandidateBySSN(Integer ssn) {
		if (ssn == null) {
			throw new IllegalArgumentException("Invalid ssn to search candidate");
		}
		return this.candidateRepository.findBySsn(ssn);
	}

	@Override
	public Candidate findCandidateByEmail(String email) {
		if (email == null) {
			throw new IllegalArgumentException("Invalid data.");
		}
		return this.candidateRepository.findByEmail(email);
	}

	@Override
	public List<Candidate> searchCandidateLike(String searchStr) {
		if (searchStr == null) {
			throw new IllegalArgumentException("Invalid data.");
		}
		return this.candidateRepository.searchCandidateLike(searchStr);
	}

	@Override
	@Transactional
	public void removeCandidate(String email) {
		if (email == null) {
			throw new IllegalArgumentException("Invalid email");
		}
		Candidate c = this.candidateRepository.findByEmail(email);
		if (c == null) {
			throw new IllegalArgumentException("User not found.");
		}
		this.candidateRepository.delete(c);
	}

}
