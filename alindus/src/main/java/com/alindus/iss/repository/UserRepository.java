package com.alindus.iss.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alindus.iss.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
