package com.login.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.login.project.model.User;

public interface UserRepository extends JpaRepository<User, Integer>  {

	List<User> findByName(String name);

	List<User> findByEmail(String email);

	User findByEmailAndPassword(String email, String password);
	
	/////Customized SQL queries
	
	

}
