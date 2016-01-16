package com.alindus.iss.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alindus.iss.BaseTest;
import com.alindus.iss.domain.Address;
import com.alindus.iss.domain.Candidate;
import com.alindus.iss.domain.Candidate.CandidateStatus;
import com.alindus.iss.domain.Phone;
import com.alindus.iss.domain.SocialSecurityNumber;
import com.alindus.iss.domain.Technology;
import com.alindus.iss.service.CandidateService;

public class CandidateServiceTest extends BaseTest {

	private static final String FIRST_NAME = "Bharat";
	private static final String MIDDLE_NAME = "Bahadur";
	private static final String LAST_NAME = "Thapa";
	private static final String EMAIL = "bharat.thapa1@gmail.com";
	private static final String SKYPE_ID = "bharat.thapa";
	private static final CandidateStatus STATUS = CandidateStatus.MARKETING;

	private static final String AREA_CODE = "641";
	private static final String PREFIX_VALUE = "451";
	private static final String NUMBER = "4753";

	private static final String ADDRESS = "1000 N 4th St";
	private static final String CITY = "fairfield";
	private static final String STATE = "IA";
	private static final String ZIP_CODE = "52557";

	private static final String SSN_FIRST_VAL = "123";
	private static final String SSN_SECOND_VAL = "12";
	private static final String SSN_THIRD_VAL = "1234";

	@Autowired
	private CandidateService candidateService;

	@Before
	public void addCandidateTest() {
		Address address = new Address(ADDRESS, CITY, STATE, ZIP_CODE);
		Phone phone = new Phone(AREA_CODE, PREFIX_VALUE, NUMBER);
		SocialSecurityNumber ssn = new SocialSecurityNumber(SSN_FIRST_VAL, SSN_SECOND_VAL, SSN_THIRD_VAL);
		Candidate candidate = new Candidate(FIRST_NAME, LAST_NAME, EMAIL, address, phone, ssn, SKYPE_ID, STATUS);
		candidate.setTechnology(new Technology("JAVA"));
		System.out.println("candidate =================="+ candidate);
		this.candidateService.add(candidate);
		Candidate c1 = this.candidateService.findCandidateByEmail(EMAIL);
		Candidate c2 = this.candidateService.findCandidateBySSN(SSN_THIRD_VAL);
		// System.out.println(this.candidateService.searchCandidateLike("Bha").size());
		Assert.assertEquals(c1.getEmail(), c2.getEmail());
	}

	@Test
	public void updateCandidateTest() {
		Candidate c = this.candidateService.findCandidateByEmail(EMAIL);
		c.setMiddleName(MIDDLE_NAME);
		c.setStatus(CandidateStatus.WORKING);
		c.setPhone1(new Phone("319", "614", "2805"));
		c.setEmail1("bharat123@gmail.com");
		c.setSkypeId1("bharati");
		this.candidateService.update(c);
		Candidate c1 = this.candidateService.findCandidateByEmail(EMAIL);
		Assert.assertTrue(c1 != null);
	}

	@After
	public void removeCandidateTest() {
		Candidate c = this.candidateService.findCandidateByEmail(EMAIL);
		this.candidateService.removeCandidate(c.getEmail());
	}
}
