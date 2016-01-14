package com.alindus.iss.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.alindus.iss.BaseTest;
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

@org.junit.FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InterviewServiceTest extends BaseTest {
	private static final String FIRSTNAME = "Amul";
	private static final String LASTNAME = "Sapkota";
	private static final String VC_EMAIL = "amul.shapkota@gmail.com";
	private static final String MARKETING_EMAIL = "prabin.adhikari@gmail.com";
	private static final String CANDIDATE_EMAIL = "bharat.thapa@gmail.com";
	private static final String CAL_TAKER = "rajib.gandi@gmail.com";
	@Autowired
	private InterviewService interviewService;
	@Autowired
	private UserService userService;
	@Autowired
	private CandidateService candidateService;

	@Test
	public void testA() {
		User user = new User(MARKETING_EMAIL, "prabin@123", "prabin@123", Role.ROLE_ADMIN);
		user.setEnable(true);
		user.setFirstName("Prabin");
		user.setLastName("Adhikari");
		this.userService.add(user);
		User user1 = new User(VC_EMAIL, "amul@123", "amul@123", Role.ROLE_VC);
		user1.setEnable(true);
		user1.setFirstName(FIRSTNAME);
		user1.setLastName(LASTNAME);
		this.userService.add(user1);
		User user2 = new User(CAL_TAKER, "rajib@123", "rajib@123", Role.ROLE_CALLTAKER);
		user2.setEnable(true);
		user2.setFirstName("Rajib");
		user2.setLastName("Ghimire");
		this.userService.add(user2);
		SocialSecurityNumber ssn = new SocialSecurityNumber("4567");
		Candidate candidate = new Candidate(CANDIDATE_EMAIL, "Thapa", CANDIDATE_EMAIL, null, null, ssn, "bharat.thapa",
				CandidateStatus.MARKETING);
		candidate.setTechnology(new Technology("DOT NET"));
		this.candidateService.add(candidate);
	}

	@Test
	public void testBaddInterviewTest() {
		User vc = this.userService.findUserByEmail(VC_EMAIL);
		User marketing = this.userService.findUserByEmail(MARKETING_EMAIL);
		User callTaker = this.userService.findUserByEmail(CAL_TAKER);
		Candidate candidate = this.candidateService.findCandidateByEmail(CANDIDATE_EMAIL);
		Client client = new Client("Microsoft");
		Vendor vendor = new Vendor("Infosys");
		Technology technology = new Technology("JAVA");
		Round round = new Round("First");
		InterviewType interviewType = new InterviewType("Phone");
		Interview interview = new Interview(candidate, client, vendor, vc, marketing, technology, null,
				InterviewStatus.PENDING);

		InterviewRound iRound = new InterviewRound(round, InterviewStatus.PENDING, interviewType, new Date(), interview,
				callTaker);
		List<InterviewRound> list = new ArrayList<>();
		list.add(iRound);
		interview.setInterviewRound(list);
		interviewService.add(interview);
		Interview i = this.interviewService.findAll().get(0);
		assertEquals(i.getTechnology().getName(), "JAVA");
		System.out.println(i.getTechnology());
	}

	@Test
	public void testCupdateInterviewTest() {
		List<Interview> interviews = this.interviewService.findAll();
		if (interviews.size() > 0) {
			Interview interview = interviews.get(0);
			System.out.println(interview.getInterviewRound().size());
			User callTaker = this.userService.findUserByEmail(CAL_TAKER);
			for (InterviewRound ir : interview.getInterviewRound()) {
				if (ir.getRound().getName().equals("Second")) {
					ir.setStatus(InterviewStatus.APPROVED);
				}
			}
			InterviewRound interviewRound = new InterviewRound(new Round("Third"), InterviewStatus.PENDING,
					new InterviewType("Skype"), new Date(), interview, callTaker);
			interview.addInterviewRound(interviewRound);
			for (InterviewRound ir : interview.getInterviewRound()) {
				System.out.println(ir.getStatus());
			}
			this.interviewService.update(interview);
			Interview interview1 = this.interviewService.findOne(1L);

			assertEquals(interview1.getInterviewRound().size(), 2);
		}
	}

	// @Test
	public void testDupdateInterviewRund() {
		Interview interview = this.interviewService.findOne(1L);
		InterviewRound interviewRound = null;
		for (InterviewRound ir : interview.getInterviewRound()) {
			if (ir.getRound().getName().equals("First")) {
				ir.setStatus(InterviewStatus.APPROVED);
				interviewRound = ir;
				break;
			}
		}
		this.interviewService.updateInterviewRound(interviewRound);
	}

	@Test
	public void testEAutoCompleteMethods() {
		for (Client v : this.interviewService.findClientsByNameLike("M")) {
			System.out.println("Client: " + v.getId() + " " + v.getName());
		}
		for (Vendor v : this.interviewService.findVendorsByNameLike("I")) {
			System.out.println("Vendor: " + v.getId() + " " + v.getName());
		}
		for (Technology v : this.interviewService.findTechnologiesByNameLike("j")) {
			System.out.println("Technology: " + v.getId() + " " + v.getName());
		}
		for (InterviewType v : this.interviewService.findInterviewsTypeByTypeLike("p")) {
			System.out.println("Interview Type: " + v.getId() + " " + v.getType());
		}
		for (Round v : this.interviewService.findRoundsByNameLike("F")) {
			System.out.println("Round: " + v.getId() + " " + v.getName());
		}

	}

	//@Test
	public void testZremoveData() {
		List<Interview> list = this.interviewService.findAll();
		for (Interview interview : list) {
			this.interviewService.remove(interview.getId());
		}
		this.userService.removeByEmail(MARKETING_EMAIL);
		this.userService.removeByEmail(VC_EMAIL);
		this.userService.removeByEmail(CAL_TAKER);
		this.candidateService.removeCandidate(CANDIDATE_EMAIL);
	}
}
