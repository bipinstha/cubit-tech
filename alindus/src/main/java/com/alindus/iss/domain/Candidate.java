package com.alindus.iss.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity
//@Table(name = "candidates")
public class Candidate extends Person {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(unique = true)
	private String SSN;
	private Phone phone1;
	private String email1;
	private String skypeId;
	private String skypeId1;
}
