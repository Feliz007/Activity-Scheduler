package com.felix.Activity.Tracker.ServiceImpl;

import com.felix.Activity.Tracker.DTO.TaskDTO;
import com.felix.Activity.Tracker.DTO.UserDTO;
import com.felix.Activity.Tracker.Exception.TaskNotFoundException;
import com.felix.Activity.Tracker.Exception.UserNotFoundException;
import com.felix.Activity.Tracker.Model.Task;
import com.felix.Activity.Tracker.Model.User;
import com.felix.Activity.Tracker.Repository.TaskRepository;
import com.felix.Activity.Tracker.Repository.UserRepository;
import com.felix.Activity.Tracker.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private  final TaskRepository taskRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }


    @Override
    public User registerUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        return  userRepository.save(user);
    }

    @Override
    public String loginUser(String email, String password) {
        String message = "";
        User user = getUserByEmail(email);
        if (user.getPassword().equals(password)){
          message = "Success";
        }else {
            message = "incorrect password";
        }
        return message;
    }


    @Override
    public Task createTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        return taskRepository.save(task);
    }

    @Override
    public Task updateTitleAndDescription(TaskDTO taskDTO , int id) {
        Task task = getTaskById(id);
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        return taskRepository.save(task);
    }

    @Override
    public List<Task> viewAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> viewAllTaskByStatus(String status) {
        return taskRepository.listOfTasksByStatus(status);
    }

    @Override
    public boolean deleteById(int id) {
         taskRepository.deleteById(id);
         return  true;
    }

    @Override
    public boolean updateTaskStatus(String status, int id){
        return taskRepository.updateTaskByIdAndStatus(status , id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email + "not found in the database"));
    }

    @Override
    public Task getTaskById(int id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException( "Task not found in the database"));
    }
}
