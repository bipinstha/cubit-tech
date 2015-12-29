package com.alindus.iss.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

@Entity(name = "ssns")
public class SocialSecurityNumber implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Pattern(regexp = "[0-9]{min=0,max=3}", message="first two digits of social security is not valid")
	private Integer initValue;
	@Pattern(regexp = "[0-9]{min=0,max=2}", message="mid value of social security is not valid")
	private Integer midValue;
	@Column(unique = true)
	@Pattern(regexp = "[0-9]{4}", message = "last four digits of valid social security is required")
	private Integer lastValue;

	public SocialSecurityNumber() {
	}

	public SocialSecurityNumber(Integer lastValue) {
		this.lastValue = lastValue;
	}

	public SocialSecurityNumber(Integer initValue, Integer midValue, Integer lastValue) {
		this.initValue = initValue;
		this.midValue = midValue;
		this.lastValue = lastValue;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getInitValue() {
		return initValue;
	}

	public void setInitValue(Integer initValue) {
		this.initValue = initValue;
	}

	public Integer getMidValue() {
		return midValue;
	}

	public void setMidValue(Integer midValue) {
		this.midValue = midValue;
	}

	public Integer getLastValue() {
		return lastValue;
	}

	public void setLastValue(Integer lastValue) {
		this.lastValue = lastValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((initValue == null) ? 0 : initValue.hashCode());
		result = prime * result + ((lastValue == null) ? 0 : lastValue.hashCode());
		result = prime * result + ((midValue == null) ? 0 : midValue.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SocialSecurityNumber other = (SocialSecurityNumber) obj;
		if (initValue == null) {
			if (other.initValue != null)
				return false;
		} else if (!initValue.equals(other.initValue))
			return false;
		if (lastValue == null) {
			if (other.lastValue != null)
				return false;
		} else if (!lastValue.equals(other.lastValue))
			return false;
		if (midValue == null) {
			if (other.midValue != null)
				return false;
		} else if (!midValue.equals(other.midValue))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SocialSecurityNumber [id=" + id + ", initValue=" + initValue + ", midValue=" + midValue + ", lastValue="
				+ lastValue + "]";
	}

}
