package com.alindus.iss.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
//import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;

import com.alindus.iss.domain.InterviewRound.InterviewStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "interviews")
public class Interview implements Serializable {

	private static final long serialVersionUID = -2321264184668986082L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne
	@JoinColumn
	@Valid
	private Candidate candidate;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "client_id")
	@Valid
	private Client client;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "vendor_id")
	private Vendor vendor;
	@OneToOne
	@JoinColumn
	private User vc;
	@OneToOne
	@JoinColumn
	private User marketing;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "technology_id")
	private Technology technology;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "interview")
	// @JoinTable(name="INTERVIEW_AND_ROUNDS", joinColumns =
	// {@JoinColumn(name="interview_id",referencedColumnName="id")},
	// inverseJoinColumns={@JoinColumn(name="interview_round_id",
	// referencedColumnName="id")})
	// @JsonIgnore
	@JsonManagedReference
	private List<InterviewRound> interviewRound;
	@Enumerated(EnumType.STRING)
	private InterviewStatus status;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss")
	private Date createdDate;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "MM/dd/yyyy hh:mm:ss")
	private Date modifiedDate;

	public Interview() {
		interviewRound = new ArrayList<InterviewRound>();
	}

	public Interview(Candidate candidate, Client client, Vendor vendor, User vc, User marketing, Technology technology,
			List<InterviewRound> interviewRound, InterviewStatus status) {
		this();
		this.candidate = candidate;
		this.client = client;
		this.vendor = vendor;
		this.vc = vc;
		this.marketing = marketing;
		this.technology = technology;
		this.interviewRound = interviewRound;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public User getVc() {
		return vc;
	}

	public void setVc(User vc) {
		this.vc = vc;
	}

	public User getMarketing() {
		return marketing;
	}

	public void setMarketing(User marketing) {
		this.marketing = marketing;
	}

	public Technology getTechnology() {
		return technology;
	}

	public void setTechnology(Technology technology) {
		this.technology = technology;
	}

	public List<InterviewRound> getInterviewRound() {
		return interviewRound;
	}

	public void setInterviewRound(List<InterviewRound> interviewRound) {
		this.interviewRound = interviewRound;
	}

	public void addInterviewRound(InterviewRound interviewRound) {
		this.interviewRound.add(interviewRound);
	}

	public InterviewStatus getStatus() {
		return status;
	}

	public void setStatus(InterviewStatus status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String toString() {
		return "Interview [id=" + id + ", candidate=" + candidate + ", client=" + client + ", vendor=" + vendor
				+ ", vc=" + vc + ", marketing=" + marketing + ", technology=" + technology + ", interviewRound="
				+ interviewRound + ", status=" + status + ", createdDate=" + createdDate + ", modifiedDate="
				+ modifiedDate + "]";
	}

}
