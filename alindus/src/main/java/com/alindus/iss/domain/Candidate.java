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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

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
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "phone1_id")
	@Valid
	private Phone phone1;
	
	@Email(message="not valid email address")
	private String email1;
	
	
	private String skypeId;
	private String skypeId1;
	@Enumerated(EnumType.STRING)
	
	@NotNull(message="status of candidate is required")
	private CandidateStatus status = CandidateStatus.MARKETING;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "technology_id")
	@NotNull(message="status of candidate is required")
	private Technology technology;

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

	public Technology getTechnology() {
		return technology;
	}

	public void setTechnology(Technology technology) {
		this.technology = technology;
	}

	public enum CandidateStatus {
		WORKING, MARKETING, LEFT, VACATION
	}

	@Override
	public String toString() {
		return "Candidate [id=" + id + ", ssn=" + ssn + ", phone1=" + phone1 + ", email1=" + email1 + ", skypeId="
				+ skypeId + ", skypeId1=" + skypeId1 + ", status=" + status + ", technology=" + technology + "]";
	}

}
