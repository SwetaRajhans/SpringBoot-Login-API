package com.login.project.controller;

import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.login.project.model.Task;
import com.login.project.model.User;
import com.login.project.repository.TaskRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class TaskController {

	@Autowired
	private TaskRepository taskRepository;
	
	@GetMapping("/alltask")
	public ResponseEntity<?> getAllUsers() {
		List<Task> tasklist=(List<Task>) taskRepository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(tasklist);
		}
	
	@PostMapping("/addtask")
	public ResponseEntity<?> addTask(@RequestBody Task task)
	{
		Task tasklist=taskRepository.save(task);
		return ResponseEntity.status(HttpStatus.OK).body(tasklist);
		
	}
	@DeleteMapping("/deletetask/{id}")
	public ResponseEntity<?> deleteTask(@PathVariable(value = "id") int id)
	{
		taskRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("task deleted id: "+id);
	}
	@PutMapping("updatetask/{id}")
	public ResponseEntity<?> updateTask(@PathVariable(value = "id") int id, @RequestBody Task task)
	{
		Optional<Task> tasklist=taskRepository.findById(id);
		Task taskresult=tasklist.get();
		taskresult.setTask(task.getTask().toString());
		Task taskResponse=taskRepository.save(taskresult);
		return ResponseEntity.status(HttpStatus.OK).body(taskResponse);
	}
}
