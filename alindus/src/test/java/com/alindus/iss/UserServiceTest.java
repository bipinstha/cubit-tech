package com.alindus.iss;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alindus.iss.domain.Address;
import com.alindus.iss.domain.Phone;
import com.alindus.iss.domain.Role;
import com.alindus.iss.domain.User;
import com.alindus.iss.dto.ChangePassword;
import com.alindus.iss.service.UserService;

public class UserServiceTest extends BaseTest {
	private static final String FIRST_NAME = "Binod";
	private static final String LAST_NAME = "Gurung";
	private static final String MIDDLE_NAME = "Babu";
	private static final String EMAIL = "gurung.binod@gmail.com";
	private static final Integer AREA_CODE = 641;
	private static final Integer PREFIX_VALUE = 451;
	private static final Integer NUMBER = 4568;

	private static final String PASSWORD = "binod@123";
	private static final String RE_PASSWORD = "binod@123";
	private static final String NEW_PASSWORD = "binod123";
	private static final Role ROLE = Role.ROLE_ADMIN;

	private static final String ADDRESS = "1000 N 4th St";
	private static final String CITY = "fairfield";
	private static final String STATE = "IA";
	private static final String ZIP_CODE = "52557";

	@Autowired
	private UserService userService;

	@Before
	public void addUserTest() {
		User us = new User(EMAIL, PASSWORD, RE_PASSWORD, ROLE);
		User us1 = new User("prabin.adhikari@gmail.com", "prabin@123", "prabin@123", Role.ROLE_MARKETING);
		this.userService.add(us);
		this.userService.add(us1);
		System.out.println("before execute");
		User user1 = this.userService.findUserByEmail(EMAIL);
		Assert.assertEquals(ROLE, user1.getRole());
	}

	@Test
	public void updateUserTest() {
		Phone phone = new Phone(AREA_CODE, PREFIX_VALUE, NUMBER);
		Address address = new Address(ADDRESS, CITY, STATE, ZIP_CODE);
		User user = this.userService.findUserByEmail(EMAIL);
		user.setFirstName(FIRST_NAME);
		user.setMiddleName(MIDDLE_NAME);
		user.setLastName(LAST_NAME);
		user.setPhone(phone);
		user.setAddress(address);
		this.userService.update(user);
		User user2 = this.userService.findUserByEmail(EMAIL);
		Assert.assertEquals(MIDDLE_NAME, user2.getMiddleName());

		// test change password method
		ChangePassword cp = new ChangePassword(EMAIL, PASSWORD, NEW_PASSWORD, NEW_PASSWORD);
		this.userService.updatePassword(cp);

		// test for disable user
		this.userService.disableUserByEmail(EMAIL);
		User disabledUser = this.userService.findUserByEmail(EMAIL);
		Assert.assertFalse(disabledUser.getEnable());

		// test for disable user
		this.userService.enableUserByEmail(EMAIL);
		User enableUser = this.userService.findUserByEmail(EMAIL);
		Assert.assertTrue(enableUser.getEnable());

	}

	@After
	public void removeUserTest() {
		User u = this.userService.findUserByEmail(EMAIL);
		System.out.println(u.getEmail());
		this.userService.removeByEmail(EMAIL);
	}
}
