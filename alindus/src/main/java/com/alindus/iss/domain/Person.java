package com.alindus.iss.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.validation.Valid;

@MappedSuperclass
public abstract class Person implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column(name = "first_name", unique = true)
	private String firstName;
	@Column(name = "middle_name")
	private String middleName;
	@Column(name = "last_name")
	private String lastName;
	@Column(unique = true)
	private String email;
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "phone_id")
	private Phone phone;

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

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName + ", email="
				+ email + ", phone=" + phone + "]";
	}

	public String toJson() {
		return "{\"firstName\":\"" + firstName + "\", \"middleName\":\"" + middleName + "\", \"lastName\":\"" + lastName
				+ "\", \"email\":\"" + email + "\",\"address\":" + address.toJson() + ", \"phone\":" + phone.toJson()
				+ "}";
	}

}
