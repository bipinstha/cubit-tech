package com.alindus.iss.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.alindus.iss.BaseTest;
import com.alindus.iss.domain.Address;
import com.alindus.iss.domain.Phone;
import com.alindus.iss.domain.Role;
import com.alindus.iss.domain.User;
import com.alindus.iss.dto.ChangePassword;

@org.junit.FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserValidationTest extends BaseTest {

	private static final String FIRST_NAME = "Binod";
	private static final String LAST_NAME = "Gurung";
	private static final String MIDDLE_NAME = "Babu";
	private static final String EMAIL = "abc@gmail.com";
	private static final String AREA_CODE = "641";
	private static final String PREFIX_VALUE = "451";
	private static final String NUMBER = "4568";

	private static final String PASSWORD = "binod@123";
	private static final String RE_PASSWORD = "binod@123";
	private static final String NEW_PASSWORD = "binod123";
	private static final Role ROLE = Role.ROLE_ADMIN;

	private static final String ADDRESS = "address";
	private static final String CITY = "fairfield";
	private static final String STATE = "IA";
	private static final String ZIP_CODE = "52557";

	@Autowired
	private UserService userService;

	 @Test
	 public void addUserTest() {
	 User us = new User(EMAIL, PASSWORD, RE_PASSWORD, ROLE);
	 us.setFirstName(FIRST_NAME);
	 us.setLastName(LAST_NAME);
	 this.userService.add(us);
	 System.out.println("user add");
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
		System.out.println("user update");
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
}
