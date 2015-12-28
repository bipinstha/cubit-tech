package com.alindus.iss.service;

import java.util.List;

import com.alindus.iss.domain.Role;
import com.alindus.iss.domain.User;
import com.alindus.iss.dto.ChangePassword;

public interface UserService extends AbstractService<User, Long>{
	
	public User findUserByEmail(String email);
	
	public void updatePassword(ChangePassword changePassword);
	
	public void removeByEmail(String email);
	
	public void enableUserByEmail(String email);
	
	public void disableUserByEmail(String email);
	
	public List<User> findByEnableFalse();
	
	public List<User> findByEnableTrue();
	
	public List<User> findUnApprovedUsers();
	
	public void approveUser(Role role, String email);
		
}
