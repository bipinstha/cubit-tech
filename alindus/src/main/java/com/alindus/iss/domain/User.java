package com.alindus.iss.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "users")
public class User extends Person {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String password;
	@Transient
	private String rePassword;
	@Enumerated(EnumType.STRING)
	private Role role;

	public User() {
	}

	public User(String firstName, String lastName, String email, Address address, Phone phone, String password,
			String rePassword, Role role) {
		super(firstName, lastName, email, address, phone);
		this.password = password;
		this.rePassword = rePassword;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", rePassword=" + rePassword + ", role=" + role + "]";
	}

	@Override
	public String toJson() {
		return "{\"id\":" + id + ", \"password\":\"" + password + "\", \"rePassword\":\"" + rePassword + "\", \"role\":\""
				+ role + "\"," + "\"firstName\":\"" + super.getFirstName() + "\", \"middleName\":\""
				+ super.getMiddleName() + "\", \"lastName\":\"" + super.getLastName() + "\", \"email\":\""
				+ super.getEmail() + "\",\"address\":" + super.getAddress().toJson() + ", \"phone\":"
				+ super.getPhone().toJson() + "}";
	}

}
