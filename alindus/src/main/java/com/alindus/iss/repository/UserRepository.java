package com.alindus.iss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alindus.iss.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	public User findByEmail(String email);
	
	@Query(value="update User set password = :password where email = :email")
	public void updatePassword(String email, String password);
	
}
