package com.alindus.iss.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alindus.iss.BaseTest;
import com.alindus.iss.domain.Role;
import com.alindus.iss.domain.User;

public class UserServiceApproveEmailSenderTest extends BaseTest {
	
	public static final String FIRST_NAME = "Bharat";
	public static final String MIDDLE_NAME ="Bikram";
	public static final String LAST_NAME = "Thapa";
	public static final String EMAIL = "bharat.thapa222@gmail.com";
	public static final Role ROLE = Role.ROLE_ADMIN;
	public static final String PASSWORD = "bharat";
	private static final String RE_PASSWORD = "bharat";
	
	@Autowired
	private UserService userService;
	
	@Before
	public void addUser(){
		User user = new User(EMAIL, PASSWORD, RE_PASSWORD, ROLE);
		user.setFirstName(FIRST_NAME);
		user.setLastName(LAST_NAME);
		this.userService.add(user);
		Assert.assertEquals(null, user.getEnable());
	}
	
	@Test
	public void approveUser(){
		User u1 = this.userService.findUserByEmail(EMAIL);
		System.out.println("email is "+ u1.getEmail());
		this.userService.approveUser(u1);
		User u2 = this.userService.findUserByEmail(EMAIL);
		Assert.assertEquals(true, u2.getEnable());
		System.out.println("approve user");
	}
	
	@After
	public void deleteUser(){
		System.out.println("delete user");
		this.userService.removeByEmail(EMAIL);
	}
}