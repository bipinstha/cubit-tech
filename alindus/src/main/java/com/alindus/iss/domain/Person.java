package com.alindus.iss.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@MappedSuperclass
public abstract class Person implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotEmpty(message="First name required")
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "middle_name")
	private String middleName;
	@NotEmpty(message="Last name required")
	@Column(name = "last_name")
	private String lastName;
	@Column(unique = true)
	@Email(message="Invalid Email")
	@NotEmpty(message="Email required")
	private String email;
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "phone_id")
	private Phone phone;
	@Temporal(TemporalType.TIMESTAMP)
	
	private Date createdDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;

	public Person() {
	}

	public Person(String email) {
		this.email = email;
	}

	public Person(String firstName, String lastName, String email, Address address, Phone phone) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.phone = phone;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName + ", email="
				+ email + ", phone=" + phone + "]";
	}

}
