package com.alindus.iss.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		this.userRepository.save(t);
	}

	@Override
	@Transactional
	public void update(User t) {
		// TODO Auto-generated method stub
		if (this.userRepository.findOne(t.getId()) == null) {
			throw new IllegalArgumentException("User does not exist.");
		}
		this.userRepository.save(t);
	}

	@Override
	@Transactional
	public void remove(Long obj) {
		// TODO Auto-generated method stub
		if (obj == null) {
			throw new IllegalArgumentException("Invalid id.");
		}
		this.userRepository.delete(obj);
	}

	@Override
	public User findOne(Long obj) {
		// TODO Auto-generated method stub
		return this.userRepository.findOne(obj);
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return this.userRepository.findAll();
	}

	@Override
	public User findUserByEmail(String email) {
		// TODO Auto-generated method stub
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
		if (changePassword.getOldPassword().equals(changePassword.getPassword())) {
			throw new IllegalArgumentException("New Password must be different than Old password.");
		}
		if (!changePassword.getPassword().equals(changePassword.getRePassword())) {
			throw new IllegalArgumentException("New password and Re-Password must be same.");
		}
		User user = this.userRepository.findByEmail(changePassword.getEmail());
		if (user == null) {
			throw new IllegalArgumentException("Invalid user.");
		}
		if (!user.getPassword().equals(changePassword.getOldPassword())) {
			throw new IllegalArgumentException("Old password does not match.");
		}
		this.userRepository.updatePassword(changePassword.getEmail(), changePassword.getPassword());
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

}
