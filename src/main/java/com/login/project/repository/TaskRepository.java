package com.login.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.login.project.model.Task;

public interface TaskRepository extends JpaRepository<Task, Integer>{

}
