package com.alindus.iss.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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
import com.alindus.iss.service.CandidateService;
import com.alindus.iss.service.UserService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@org.junit.FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InterviewControllerTest extends BaseTest {

	private static final String VC_EMAIL = "ram@gmail.com";
	private static final String MARKETING_EMAIL = "shyam@gmail.com";
	private static final String CANDIDATE_EMAIL = "hari@gmail.com";
	@Autowired
	private UserService userService;
	@Autowired
	private CandidateService candidateService;

	private MockMvc mvc;
	@Autowired
	private WebApplicationContext wac;

	//@Before
	public void AsetUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	//@Test
	public void testB() {
		User vc = new User(VC_EMAIL, "12345", "12345", Role.ROLE_MARKETING);
		this.userService.add(vc);
		User marketing = new User(MARKETING_EMAIL, "1234", "1234", Role.ROLE_MARKETING);
		this.userService.add(marketing);
		Candidate candidate = new Candidate("Raju", "Lama", CANDIDATE_EMAIL, null, null, new SocialSecurityNumber("9876"),
				"hari", CandidateStatus.MARKETING);
		this.candidateService.add(candidate);
	}

	//@Test
	public void testCaddInterview() throws Exception {
		Gson gson = new Gson();
		User vc = this.userService.findUserByEmail(VC_EMAIL);
		User marketing = this.userService.findUserByEmail(MARKETING_EMAIL);
		Candidate candidate = this.candidateService.findCandidateByEmail(CANDIDATE_EMAIL);
		Interview interview = new Interview(candidate, new Client("Chase Bank"), new Vendor("jp morgan"), vc, marketing,
				new Technology("JAVA"), null, InterviewStatus.PENDING);
		List<InterviewRound> list = new ArrayList<>();
		InterviewRound interviewRound = new InterviewRound(new Round("First"), InterviewStatus.PENDING,
				new InterviewType("phone"), null, null, vc);
		list.add(interviewRound);
		interview.setInterviewRound(list);
		MvcResult result = mvc
				.perform(MockMvcRequestBuilders.post("/secure/interview/add")
						.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE)
						.content(gson.toJson(interview)))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		Interview interview1 = gson.fromJson(result.getResponse().getContentAsString(), Interview.class);
		System.out.println(interview1.getStatus());
	}

	//@Test
	public void testDupdateInterview() throws Exception {
		User vc = this.userService.findUserByEmail(VC_EMAIL);
		Gson gson = new Gson();
		MvcResult result = mvc
				.perform(MockMvcRequestBuilders.get("/secure/interview/all")
						.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		List<Interview> interviews = gson.fromJson(result.getResponse().getContentAsString(),
				new TypeToken<List<Interview>>() {
				}.getType());
		if (interviews.size() > 0) {
			Interview interview = interviews.get(0);

			System.out.println(interview);
			InterviewRound round = new InterviewRound(new Round("Second"), InterviewStatus.PENDING,
					new InterviewType("Skype"), null, null, vc);
			interview.addInterviewRound(round);
			for (InterviewRound ir : interview.getInterviewRound()) {
				if (ir.getRound().getName().equals("First"))
					ir.setStatus(InterviewStatus.APPROVED);
			}
			MvcResult result1 = mvc
					.perform(MockMvcRequestBuilders.post("/secure/interview/update")
							.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE)
							.content(gson.toJson(interview)))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
					.andReturn();
			Interview interview1 = gson.fromJson(result1.getResponse().getContentAsString(), Interview.class);
			System.out.println(interview1);
		}
	}

	//@After
	public void testZremoveData() throws Exception {
		Gson gson = new Gson();
		MvcResult result = mvc
				.perform(MockMvcRequestBuilders.get("/secure/interview/all")
						.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		List<Interview> list = gson.fromJson(result.getResponse().getContentAsString(),
				new TypeToken<List<Interview>>() {
				}.getType());

		System.out.println(list);
		for (Interview i : list) {
			mvc.perform(MockMvcRequestBuilders.delete("/secure/interview/remove/{id}", i.getId())
					.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isOk());
		}
//		 this.userService.removeByEmail(VC_EMAIL);
//		 this.userService.removeByEmail(MARKETING_EMAIL);
//		 this.candidateService.removeCandidate(CANDIDATE_EMAIL);

	}
}
