package com.alindus.iss.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.alindus.iss.domain.Address;
import com.alindus.iss.domain.Phone;
import com.alindus.iss.domain.User;
import com.alindus.iss.dto.ChangePassword;
import com.alindus.iss.repository.UserRepository;
import com.alindus.iss.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public void add(User t) {
		if (t.getId() != null) {
			throw new IllegalArgumentException("User already exist.");
		}
		if (!t.getPassword().equals(t.getRePassword())) {
			throw new IllegalArgumentException("Password does not match with repassword");
		}
		t.setPassword(new BCryptPasswordEncoder().encode(t.getPassword()));
		this.userRepository.save(t);
	}

	@Override
	@Transactional
	public void update(User t) {
		User u = this.userRepository.findByEmail(t.getEmail());
		if (u == null) {
			throw new IllegalArgumentException("User does not exist.");
		}
		// Create the new object of address, phone and user with existing id
		// Don't allow password to change while update user information
		Address address = new Address(t.getAddress().getAddress(), t.getAddress().getCity(), t.getAddress().getState(),
				t.getAddress().getZipCode());
		address.setAddress1(t.getAddress().getAddress1());
		if (u.getAddress() != null)
			address.setId(u.getAddress().getId());
		Phone phone = new Phone(t.getPhone().getAreaCode(), t.getPhone().getPrefixValue(), t.getPhone().getNumber());
		if (u.getPhone() != null)
			phone.setId(u.getPhone().getId());
		User updateUser = new User(t.getFirstName(), t.getLastName(), u.getEmail(), address, phone, u.getPassword(),
				u.getPassword(), t.getRole());
		updateUser.setId(u.getId());
		updateUser.setMiddleName(t.getMiddleName());
		updateUser.setEnable(t.getEnable());
		this.userRepository.save(updateUser);
	}

	@Override
	@Transactional
	public void remove(Long obj) {
		if (obj == null) {
			throw new IllegalArgumentException("Invalid id.");
		}
		this.userRepository.delete(obj);
	}

	@Override
	public User findOne(Long obj) {
		return this.userRepository.findOne(obj);
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return this.userRepository.findAll();
	}

	@Override
	public User findUserByEmail(String email) {
		if (email == null) {
			throw new IllegalArgumentException("Email not valid.");
		}
		return this.userRepository.findByEmail(email);
	}

	@Override
	@Transactional
	public void updatePassword(ChangePassword changePassword) {
		// TODO Auto-generated method stub
		// TODO encrypt the password before compare
		if (!changePassword.getPassword().equals(changePassword.getRePassword())) {
			throw new IllegalArgumentException("New password and Re-Password must be same.");
		}
		if (changePassword.getOldPassword().equals(changePassword.getPassword())) {
			throw new IllegalArgumentException("New Password must be different than Old password.");
		}
		User user = this.userRepository.findByEmail(changePassword.getEmail());
		if (user == null) {
			throw new IllegalArgumentException("Invalid user.");
		}
		if (!new BCryptPasswordEncoder().matches(changePassword.getOldPassword(), user.getPassword())) {
			throw new IllegalArgumentException("Old password does not match.");
		}
		this.userRepository.updatePassword(changePassword.getEmail(),
				new BCryptPasswordEncoder().encode(changePassword.getPassword()));
	}

	@Override
	@Transactional
	public void removeByEmail(String email) {
		User user = this.userRepository.findByEmail(email);
		if (user == null) {
			throw new IllegalArgumentException("User not found.");
		}
		this.userRepository.delete(user);
	}

	@Override
	@Transactional
	public void enableUserByEmail(String email) {
		User user = this.userRepository.findByEmail(email);
		if (user == null) {
			throw new IllegalArgumentException("User not found.");
		}
		this.userRepository.enableUser(email);
	}

	@Override
	@Transactional
	public void disableUserByEmail(String email) {
		User user = this.userRepository.findByEmail(email);
		if (user == null) {
			throw new IllegalArgumentException("User not found.");
		}
		this.userRepository.disableUser(email);
	}

	@Override
	public List<User> findByEnableFalse() {	
		return this.userRepository.findByEnableFalse();
	}

	@Override
	public List<User> findByEnableTrue() {
		return this.userRepository.findByEnableTrue();
	}

}
