package com.alindus.iss.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alindus.iss.domain.Role;
import com.alindus.iss.domain.User;
import com.alindus.iss.dto.ChangePassword;
import com.alindus.iss.service.UserService;
import com.alindus.iss.utils.SpringUtils;

@RestController
@RequestMapping(value = "/secure/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseStatus(HttpStatus.OK)
public class UserController {

	Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public User addUser(@RequestBody User user) {
		try {
			this.userService.add(user);
			return user;
		} catch (IllegalArgumentException ex) {
			this.logger.error(ex.getMessage());
			throw new IllegalArgumentException(ex.getMessage());
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public User updateUser(@RequestBody User user) {
		try {
			this.userService.update(user);
			return user;
		} catch (IllegalArgumentException ex) {
			this.logger.error(ex.getMessage());
			throw new IllegalArgumentException(ex.getMessage());
		}
	}

	@RequestMapping(value = "/{email}/find", method = RequestMethod.GET)
	public User findUserByEmail(@PathVariable String email) {

		try {
			return this.userService.findUserByEmail(email);
		} catch (IllegalArgumentException ex) {
			this.logger.error(ex.getMessage());
			throw new IllegalArgumentException(ex.getMessage());
		}
	}

	@RequestMapping(value = "/{email}/remove", method = RequestMethod.DELETE)
	public void removeUserByEmail(@PathVariable String email) {
		try {
			this.userService.removeByEmail(email);
		} catch (IllegalArgumentException ex) {
			this.logger.error(ex.getMessage());
			throw new IllegalArgumentException(ex.getMessage());
		}
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<User> getAllUser() {
		try {
			return this.userService.findAll();
		} catch (IllegalArgumentException ex) {
			this.logger.error(ex.getMessage());
			throw new IllegalArgumentException(ex.getMessage());
		}
	}

	@RequestMapping(value = "/enablefalse", method = RequestMethod.GET)
	public List<User> getAllEnableFalseUsers() {
		try {
			return this.userService.findByEnableFalse();
		} catch (IllegalArgumentException ex) {
			this.logger.error(ex.getMessage());
			throw new IllegalArgumentException(ex.getMessage());
		}
	}

	@RequestMapping(value = "/enabletrue", method = RequestMethod.GET)
	public List<User> getAllEnableTrueUsers() {
		try {
			return this.userService.findByEnableTrue();
		} catch (IllegalArgumentException ex) {
			this.logger.error(ex.getMessage());
			throw new IllegalArgumentException(ex.getMessage());
		}
	}

	@RequestMapping(value = "/password/change", method = RequestMethod.POST)
	public void changePassword(@RequestBody ChangePassword changePassword) {
		try {
			this.userService.updatePassword(changePassword);
		} catch (IllegalArgumentException ex) {
			this.logger.error(ex.getMessage());
			throw new IllegalArgumentException(ex.getMessage());
		}
	}

	@RequestMapping(value = "/mydetail", method = RequestMethod.GET)
	public User myDetail() {
		System.out.println(SpringUtils.getUserName());
		if (SpringUtils.getUserName() == null) {
			throw new IllegalArgumentException("User is not login.");
		}
		try {
			return this.userService.findUserByEmail(SpringUtils.getUserName());
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException(ex.getMessage());
		}
	}

	@RequestMapping(value = "/all/roles", method = RequestMethod.GET)
	public Role[] getAllRoles() {
		return Role.values();
	}

	@RequestMapping(value = "/unapproved", method = RequestMethod.GET)
	public List<User> findUnApprovedUsers() {
		try {
			return this.userService.findUnApprovedUsers();
		} catch (IllegalArgumentException ex) {
			this.logger.error(ex.getMessage());
			throw new IllegalArgumentException(ex.getMessage());
		}
	}

	@RequestMapping(value = "/approve", method = RequestMethod.POST)
	public void approveUser(@RequestBody User user) {
		try {
			this.userService.approveUser(user);
		} catch (IllegalArgumentException ex) {
			this.logger.error(ex.getMessage());
			throw new IllegalArgumentException(ex.getMessage());
		}
	}
}
