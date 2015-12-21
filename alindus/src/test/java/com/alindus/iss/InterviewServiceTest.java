package com.alindus.iss;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.alindus.iss.domain.Candidate;
import com.alindus.iss.domain.Candidate.CandidateStatus;
import com.alindus.iss.domain.Client;
import com.alindus.iss.domain.Interview;
import com.alindus.iss.domain.InterviewRound;
import com.alindus.iss.domain.InterviewRound.InterviewStatus;
import com.alindus.iss.domain.InterviewType;
import com.alindus.iss.domain.Role;
import com.alindus.iss.domain.Round;
import com.alindus.iss.domain.SocialSecurityNumber;
import com.alindus.iss.domain.Technology;
import com.alindus.iss.domain.User;
import com.alindus.iss.domain.Vendor;
import com.alindus.iss.service.CandidateService;
import com.alindus.iss.service.InterviewService;
import com.alindus.iss.service.UserService;

@org.junit.FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InterviewServiceTest extends BaseTest {

	private static final String VC_EMAIL = "amul.shapkota@gmail.com";
	private static final String MARKETING_EMAIL = "prabin.adhikari@gmail.com";
	private static final String CANDIDATE_EMAIL = "bharat.thapa@gmail.com";
	private static final String INTERVIEWER = "Rajib Gandi";
	@Autowired
	private InterviewService interviewService;
	@Autowired
	private UserService userService;
	@Autowired
	private CandidateService candidateService;

	// @Test
	public void ainsertValues() {
		User user = new User(MARKETING_EMAIL, "prabin@123", "prabin@123", Role.ROLE_MARKETING);
		user.setEnable(true);
		this.userService.add(user);
		User user1 = new User(VC_EMAIL, "amul@123", "amul@123", Role.ROLE_VC);
		user1.setEnable(true);
		this.userService.add(user1);
		SocialSecurityNumber ssn = new SocialSecurityNumber(4567);
		Candidate candidate = new Candidate(CANDIDATE_EMAIL, "Thapa", CANDIDATE_EMAIL, null, null, ssn, "bharat.thapa",
				CandidateStatus.MARKETING);
		this.candidateService.add(candidate);
	}

	@Test
	public void baddInterviewTest() {

		User vc = this.userService.findUserByEmail(VC_EMAIL);
		User marketing = this.userService.findUserByEmail(MARKETING_EMAIL);
		Candidate candidate = this.candidateService.findCandidateByEmail(CANDIDATE_EMAIL);
		Client client = new Client("Microsoft");
		Vendor vendor = new Vendor("Infosys");
		Technology technology = new Technology("JAVA");
		Round round = new Round("First");
		InterviewType interviewType = new InterviewType("Phone");
		Interview interview = new Interview(candidate, client, vendor, vc, marketing, technology, null,
				InterviewStatus.PENDING);

		InterviewRound iRound = new InterviewRound(round, InterviewStatus.PENDING, interviewType, new Date(), interview,
				INTERVIEWER);
		List<InterviewRound> list = new ArrayList<>();
		list.add(iRound);
		interview.setInterviewRound(list);
		interviewService.add(interview);
		Interview i = this.interviewService.findOne(1L);
		System.out.println(i.getTechnology());
		System.out.println(i.getCandidate());
		System.out.println(i.getClient());
		System.out.println(i.getMarketing());
		System.out.println(i.getStatus());
		System.out.println(i.getVc());
		System.out.println(i.getVendor());
		for (InterviewRound ir : i.getInterviewRound()) {
			System.out.println(ir.getInterviewer());
			// System.out.println(ir.getInterview());
			System.out.println(ir.getInterviewDate());
			System.out.println(ir.getInterviewType());
			System.out.println(ir.getRound());
			System.out.println(ir.getStatus());
		}

	}

	@Test
	public void testVendors() {
		for (Round v : this.interviewService.findRoundsByNameLike("F")) {
			System.out.println(v.getId() + " " + v.getName());
		}
	}

	// @After
	public void cremoveDataTest() {
		this.userService.removeByEmail(MARKETING_EMAIL);
		this.userService.removeByEmail(VC_EMAIL);
		this.candidateService.removeCandidate(CANDIDATE_EMAIL);
	}
}
