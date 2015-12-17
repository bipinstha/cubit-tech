package com.alindus.iss;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

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

import com.alindus.iss.domain.Address;
import com.alindus.iss.domain.Phone;
import com.alindus.iss.domain.Role;
import com.alindus.iss.domain.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class UserControllerTest extends BaseTest {

	private static final String FIRST_NAME = "Bipin";
	private static final String LAST_NAME = "Shrestha";
	private static final String EMAIL = "aakee.stha@gmail.com";
	private static final Integer AREA_CODE = 469;
	private static final Integer PREFIX_VALUE = 346;
	private static final Integer NUMBER = 1618;

	private static final String PASSWORD = "password";
	private static final String RE_PASSWORD = "password";
	private static final Role ROLE = Role.ROLE_ADMIN;

	private static final String ADDRESS = "1000 N 4th St";
	private static final String CITY = "fairfield";
	private static final String STATE = "IA";
	private static final String ZIP_CODE = "52557";

	private MockMvc mvc;
	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void addUserTest() throws Exception {
		User user = new User(EMAIL, PASSWORD, RE_PASSWORD, ROLE);
		user.setEnable(true);
		user.setEnable(true);
		Gson gson = new Gson();
		String json = gson.toJson(user);
		System.out.println("json obj: " + json);
		MvcResult result = mvc
				.perform(MockMvcRequestBuilders.post("/secure/user/add").contentType(MediaType.APPLICATION_JSON_VALUE)
						.accept(MediaType.APPLICATION_JSON_VALUE).content(json))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		System.out.println(result.getResponse().getContentAsString());
		User user1 = gson.fromJson(result.getResponse().getContentAsString(), User.class);
		System.out.println("obj: " + user1.getEmail());
	}

	@Test
	public void updateUserTest() throws Exception {
		Gson gson = new Gson();
		MvcResult result = mvc
				.perform(MockMvcRequestBuilders.get("/secure/user/{email}/find", EMAIL)
						.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		Phone phone = new Phone(AREA_CODE, PREFIX_VALUE, NUMBER);
		Address address = new Address(ADDRESS, CITY, STATE, ZIP_CODE);
		// result.getResponse().
		System.out.println("response: " + result.getResponse().getContentAsString());
		User user = gson.fromJson(result.getResponse().getContentAsString(), User.class);
		user.setFirstName(FIRST_NAME);
		user.setLastName(LAST_NAME);
		user.setAddress(address);
		user.setPhone(phone);
		System.out.println("User Json:  " + gson.toJson(user));
		mvc.perform(MockMvcRequestBuilders.post("/secure/user/update").contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE).content(gson.toJson(user))).andExpect(status().isOk());
	}

	@Test
	public void viewAllTest() throws Exception {
		Gson gson = new Gson();
		MvcResult result = mvc
				.perform(MockMvcRequestBuilders.get("/secure/user/all").contentType(MediaType.APPLICATION_JSON_VALUE)
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		List<User> list = gson.fromJson(result.getResponse().getContentAsString(), new TypeToken<List<User>>() {
		}.getType());
		System.out.println("user list: " + list.toString());
	}

}
