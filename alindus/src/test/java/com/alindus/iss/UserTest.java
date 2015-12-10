package com.alindus.iss;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alindus.iss.domain.Address;
import com.alindus.iss.domain.Phone;
import com.alindus.iss.domain.Role;
import com.alindus.iss.domain.User;

public class UserTest extends BaseTest {

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

	// @Autowired
	// private PersonController personController;

	private MockMvc mvc;
	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void addUser() throws Exception {
		Phone phone = new Phone(AREA_CODE, PREFIX_VALUE, NUMBER);
		Address address = new Address(ADDRESS, CITY, STATE, ZIP_CODE);
		User user = new User(FIRST_NAME, LAST_NAME, EMAIL, address, phone, PASSWORD, RE_PASSWORD, ROLE);
//		Person person = new Person(FIRST_NAME, LAST_NAME, EMAIL, address, phone);
		// GsonFactoryBean gson = new GsonFactoryBean();
		// gson.
		System.out.println(user.toJson());
		// String person1 = "{\"firstName\" : \"Naren\", \"lastName\" :
		// \"Thapa\", \"email\" : \"naren@gmail.com\", \"phone\" : {\"areaCode\"
		// : 879, \"prefixValue\" : 345, \"number\" : 9898}}";
		mvc.perform(MockMvcRequestBuilders.post("/user/add").contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE).content(user.toJson())).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
				// .andExpect(jsonPath("$.id", is("1")));
				// System.out.println(p);
				// mvc.perform(MockMvcRequestBuilders.get("/")
				// .accept(MediaType.APPLICATION_JSON))
				// .andExpect(status().isOk())
				// .andExpect(content().string(equalTo("Greetings from Spring
				// Boot!")));
				// this.personController.addPerson(person);

		// Person p1 = this.personController.getOnePerson(1L);
		// Assert.assertEquals(p, person);
	}

}
