package com.alindus.iss;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alindus.iss.domain.Address;
import com.alindus.iss.domain.Phone;
import com.alindus.iss.domain.Role;
import com.alindus.iss.domain.User;
import com.alindus.iss.dto.ChangePassword;
import com.alindus.iss.service.UserService;

public class UserServiceTest extends BaseTest{
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
	
	@Test
	public void addUserTest(){
		Phone phone = new Phone(AREA_CODE, PREFIX_VALUE, NUMBER);
		Address address = new Address(ADDRESS, CITY, STATE, ZIP_CODE);
		User user = new User(FIRST_NAME, LAST_NAME, EMAIL, address, phone, PASSWORD, RE_PASSWORD, ROLE);
		this.userService.add(user);
		User user1 = this.userService.findUserByEmail(EMAIL);
		Assert.assertEquals(FIRST_NAME, user1.getFirstName());
		user1.setMiddleName(MIDDLE_NAME);
		this.userService.update(user1);
		User user2 = this.userService.findUserByEmail(EMAIL);
		Assert.assertEquals(MIDDLE_NAME, user2.getMiddleName());
		ChangePassword cp = new ChangePassword(EMAIL, PASSWORD, NEW_PASSWORD, NEW_PASSWORD);
		this.userService.updatePassword(cp);
		
		
		this.userService.removeByEmail(EMAIL);
	}
}
