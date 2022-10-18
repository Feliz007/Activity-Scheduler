package com.felix.Activity.Tracker.Service;

import com.felix.Activity.Tracker.DTO.TaskDTO;
import com.felix.Activity.Tracker.DTO.UserDTO;
import com.felix.Activity.Tracker.Model.Task;
import com.felix.Activity.Tracker.Model.User;

import java.util.List;

public interface UserService {

    User registerUser(UserDTO userDTO);

    String loginUser(String email, String password);

    Task createTask(TaskDTO taskDTO);

    Task updateTitleAndDescription(TaskDTO taskDTO , int id);

    Task getTaskById(int id);

    List<Task> viewAllTasks();

    boolean updateTaskStatus(String status, int id);

    List<Task> viewAllTaskByStatus(String status);

    boolean deleteById(int id);
    User getUserByEmail(String email);

}
