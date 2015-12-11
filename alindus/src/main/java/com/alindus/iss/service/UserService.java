package com.alindus.iss.service;

import com.alindus.iss.domain.User;
import com.alindus.iss.dto.ChangePassword;

public interface UserService extends AbstractService<User, Long>{
	
	public User findUserByEmail(String email);
	
	public void updatePassword(ChangePassword changePassword);
	
	public void removeByEmail(String email);
	
	public void enableUserByEmail(String email);
	
	public void disableUserByEmal(String email);

}
