package com.alindus.iss.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alindus.iss.domain.User;
import com.alindus.iss.repository.UserRepository;
import com.alindus.iss.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public void add(User t) {
		if(t.getId() != null){
			throw new IllegalArgumentException("Person already exist.");
		}
		this.userRepository.save(t);
	}

	@Override
	@Transactional
	public void update(User t) {
		// TODO Auto-generated method stub
		if(this.userRepository.findOne(t.getId()) == null){
			throw new IllegalArgumentException("Person does not exist.");
		}
		this.userRepository.save(t);
	}

	@Override
	@Transactional
	public void remove(Long obj) {
		// TODO Auto-generated method stub
		if(obj == null){
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

}
