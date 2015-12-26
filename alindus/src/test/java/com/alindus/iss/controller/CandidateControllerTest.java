package com.alindus.iss.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alindus.iss.BaseTest;
import com.alindus.iss.domain.Address;
import com.alindus.iss.domain.Candidate;
import com.alindus.iss.domain.Phone;
import com.alindus.iss.domain.SocialSecurityNumber;
import com.alindus.iss.domain.Technology;
import com.google.gson.Gson;

public class CandidateControllerTest extends BaseTest {

	private static final String CANDIDATE_EMAIL = "amulsapkota@gmail.com";

	private MockMvc mvc;
	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void addCandidateTest() throws Exception {

		Phone phone = new Phone(641, 345, 1212);
		Phone phone1 = new Phone(641, 345, 1212);
		SocialSecurityNumber ssn = new SocialSecurityNumber(777, 34, 6666);
		Address address = new Address("1010 N 4th street", "FF", "IA", "23456");

		Candidate candidate = new Candidate("amul", "sapkota", CANDIDATE_EMAIL, address, phone, ssn, "skypeId",
				Candidate.CandidateStatus.MARKETING);
		candidate.setSsn(ssn);
		candidate.setPhone(phone);
		candidate.setPhone1(phone1);
		candidate.setEmail1("amul@gmail.com");
		candidate.setSkypeId1("skypeId1");
		candidate.setTechnology(new Technology("JAVA"));

		Gson gson = new Gson();
		String json = gson.toJson(candidate);

		MvcResult result = mvc
				.perform(MockMvcRequestBuilders.post("/secure/candidate/add")
						.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE)
						.content(json))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		Candidate candidate1 = gson.fromJson(result.getResponse().getContentAsString(), Candidate.class);
		System.out.println(candidate1);
	}

	@Test
	public void updateCandidateTest() throws Exception {
		Gson gson = new Gson();
		MvcResult result = mvc
				.perform(MockMvcRequestBuilders.get("/secure/candidate/{email}/find", CANDIDATE_EMAIL)
						.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		Phone phone = new Phone(111, 111, 2222);
		Phone phone1 = new Phone(222, 222, 2222);
		SocialSecurityNumber ssn = new SocialSecurityNumber(111, 11, 1111);
		Address address = new Address("4146 N Belt Line", "Irvin", "Dallas", "75038");
		Candidate can1 = gson.fromJson(result.getResponse().getContentAsString(), Candidate.class);
		can1.setFirstName("Edited Amul");
		can1.setSkypeId("Edited Skype Id");
		can1.setSsn(ssn);
		can1.setPhone(phone);
		can1.setAddress(address);
		can1.setPhone1(phone1);
		MvcResult result1 = mvc
				.perform(MockMvcRequestBuilders.post("/secure/candidate/update")
						.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE)
						.content(gson.toJson(can1)))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		Candidate candidate1 = gson.fromJson(result1.getResponse().getContentAsString(), Candidate.class);
		System.out.println(candidate1);
	}

	@Test
	public void removeCandidateTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete("/secure/candidate/{email}/remove", CANDIDATE_EMAIL)
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk());
	}
}
