package com.example.login_auth_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.login_auth_api.domain.task.Task;
import com.example.login_auth_api.domain.user.User;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {
    
    List<Task> findByUser(User user);
}
