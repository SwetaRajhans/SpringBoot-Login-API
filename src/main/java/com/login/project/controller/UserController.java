package com.login.project.controller;

import java.util.List;
import java.util.Optional;

import javax.crypto.EncryptedPrivateKeyInfo;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.login.project.model.User;
import com.login.project.repository.UserRepository;
import com.login.project.utilities.Encryption;

@RestController
@RequestMapping("/login")
public class UserController{
	
	@Autowired
	private UserRepository userRepository;
	
	
	@GetMapping("/getAllUsers")
	public List<User> getAllUsers() {
		return (List<User>) userRepository.findAll();
	}
	
	@GetMapping("/getUserById/{id}")
	public Optional<User> getUserById(@PathVariable(value = "id") Integer id) {
		return userRepository.findById(id);
	}
	
	@GetMapping("/getUsersByName/{name}")
	public List<User> getUsersByName(@PathVariable(value = "name") String name) {
		 return userRepository.findByName(name);
		
		
	}
	
	@GetMapping("/userbyemail")
	public ResponseEntity<?> getUserByEmail(@RequestParam String email,@RequestParam String password){
		String encpwd=Encryption.encryptPassword(password);
		
		User user=userRepository.findByEmailAndPassword(email,encpwd);
		if(user != null) {
			
		return ResponseEntity.status(HttpStatus.OK).body(user);
		}
		else
			return ResponseEntity.status(HttpStatus.OK).body("Invalid user name or password");
	
		
	}
	@PostMapping("/createUser")
	public ResponseEntity<?> createUser(@Valid @RequestBody User user )
	{
		String password=user.getPassword();
		String encpwd=Encryption.encryptPassword(password);
		user.setPassword(encpwd);
		List userList=userRepository.findByEmail(user.getEmail().toString());
		if(userList.size()>0) {
			return ResponseEntity.status(200).body("user already exist");
			
		}
		else {
		return ResponseEntity.status(200).body(userRepository.save(user));
		}
	}
	
	@PutMapping("/updateUser/{id}")
	public ResponseEntity<?> updateUser( @PathVariable(value = "id") Integer id, @RequestBody User user){
		   Optional<User> userlist=userRepository.findById(id);
		   User result=userlist.get();
		   result.setPassword(user.getPassword().toString());
		   result.setmNo(user.getmNo().toString());
		   result.setName(user.getName().toString());
		   return ResponseEntity.status(200).body(userRepository.save(result));
	}
	
	
	
	
		
	

}
