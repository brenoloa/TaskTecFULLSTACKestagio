package com.example.login_auth_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.login_auth_api.domain.task.Task;
import com.example.login_auth_api.domain.user.User;
import com.example.login_auth_api.repositories.TaskRepository;
import com.example.login_auth_api.repositories.UserRepository; 

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository; 

    
    public List<Task> getTasksForUser(User user) {
        
        String loggedInUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        
        User loggedInUser = userRepository.findByEmail(loggedInUserEmail)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!")); 

        
        return taskRepository.findByUser(loggedInUser);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(String taskId) {
        taskRepository.deleteById(taskId);
    }

    public Task getTaskById(String taskId) {
        return taskRepository.findById(taskId).orElse(null); 
    }
}
