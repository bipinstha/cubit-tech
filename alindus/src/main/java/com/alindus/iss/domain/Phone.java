package com.alindus.iss.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

@Entity(name = "phones")
public class Phone implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "area_code")
	
	@Pattern(regexp = "[0-9]{3}", message = "invalid area code")
	private String areaCode;
	@Pattern(regexp="[0-9]{3}", message="Invalid prefix of phone number")
	@Column(name = "prefix_value")
	private String prefixValue;
	
	@Pattern(regexp="[0-9]{4}", message="Invalid suffix of phone number")
	private String number;

	
	public Phone() {
	}
	public Phone(String areaCode, String prefixValue, String number) {
		this.areaCode = areaCode;
		this.prefixValue = prefixValue;
		this.number = number;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getPrefixValue() {
		return prefixValue;
	}

	public void setPrefixValue(String prefixValue) {
		this.prefixValue = prefixValue;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Phone [id=" + id + ", areaCode=" + areaCode + ", prefixValue=" + prefixValue + ", number=" + number
				+ "]";
	}

	public String toJson() {
		return "{\"id\":" + id + ", \"areaCode\":" + areaCode + ", \"prefixValue\":" + prefixValue + ", \"number\":"
				+ number + "}";
	}

}
