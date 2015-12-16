package com.alindus.iss.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;

@Entity
@Table(name = "candidates")
public class Candidate extends Person {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ssn_id")
	private SocialSecurityNumber ssn;
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "phone1_id")
	private Phone phone1;
	private String email1;
	private String skypeId;
	private String skypeId1;
	@Enumerated(EnumType.STRING)
	private CandidateStatus status = CandidateStatus.MARKETING;

	public Candidate() {
		super();
	}

	public Candidate(String firstName, String lastName, String email, Address address, Phone phone,
			SocialSecurityNumber ssn, String skypeId, CandidateStatus status) {
		super(firstName, lastName, email, address, phone);
		this.ssn = ssn;
		this.skypeId = skypeId;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SocialSecurityNumber getSsn() {
		return ssn;
	}

	public void setSsn(SocialSecurityNumber ssn) {
		this.ssn = ssn;
	}

	public Phone getPhone1() {
		return phone1;
	}

	public void setPhone1(Phone phone1) {
		this.phone1 = phone1;
	}

	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getSkypeId() {
		return skypeId;
	}

	public void setSkypeId(String skypeId) {
		this.skypeId = skypeId;
	}

	public String getSkypeId1() {
		return skypeId1;
	}

	public void setSkypeId1(String skypeId1) {
		this.skypeId1 = skypeId1;
	}
	
	public CandidateStatus getStatus() {
		return status;
	}

	public void setStatus(CandidateStatus status) {
		this.status = status;
	}

	public enum CandidateStatus {
		WORKING, MARKETING, LEFT, VACATION
	}

}
