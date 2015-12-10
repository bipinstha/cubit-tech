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

import com.alindus.iss.domain.User;
import com.alindus.iss.service.UserService;

@RestController
@RequestMapping(value = "/secure/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseStatus(HttpStatus.OK)
public class UserController {

	Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public User addPerson(@RequestBody User user) {
		System.out.println(user);
		try {
			this.userService.add(user);
			return user;
		} catch (IllegalArgumentException ex) {
			this.logger.error(ex.getMessage());
			throw new IllegalArgumentException(ex.getMessage());
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public User updatePerson(@RequestBody User user) {
		try {
			this.userService.update(user);
			return user;
		} catch (IllegalArgumentException ex) {
			this.logger.error(ex.getMessage());
			throw new IllegalArgumentException(ex.getMessage());
		}
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public User getOnePerson(@PathVariable Long userId) {
		try {
			return this.userService.findOne(userId);
		} catch (IllegalArgumentException ex) {
			this.logger.error(ex.getMessage());
			throw new IllegalArgumentException(ex.getMessage());
		}
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<User> getAllPerson() {
		try {
			return this.userService.findAll();
		} catch (IllegalArgumentException ex) {
			this.logger.error(ex.getMessage());
			throw new IllegalArgumentException(ex.getMessage());
		}
	}
}
