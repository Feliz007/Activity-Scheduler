package com.felix.Activity.Tracker.ServiceImpl;

import com.felix.Activity.Tracker.DTO.TaskDTO;
import com.felix.Activity.Tracker.DTO.UserDTO;
import com.felix.Activity.Tracker.Model.Task;
import com.felix.Activity.Tracker.Model.User;
import com.felix.Activity.Tracker.Repository.TaskRepository;
import com.felix.Activity.Tracker.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.time.Month.AUGUST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    TaskRepository taskRepository;

    @InjectMocks
    UserServiceImpl userServiceImpl;

    private User user;
    private UserDTO userDTO;
    private TaskDTO taskDTO;
    private Task task;
    private  LocalDateTime time;
    List<Task> taskList;
    @BeforeEach
    void setUp() {
        time = LocalDateTime.of(2022, AUGUST,3,6,30,40,50000);
        taskList = new ArrayList<>();
        taskList.add(task);
        this.user = new User(1, "Vincent" , "elemelefelix@gmail.com" , "12345" , taskList);
        this.task = new Task(1, "Write Code" , "Code till 7am" , "pending" , time , time , time , user);
        this.taskDTO = new TaskDTO("Write Code" , "Code till 7am" );
        this.userDTO = new UserDTO("vincent" , "enwerevincent@gmail.com", "12345");
        when(userRepository.save(user)).thenReturn(user);
        when(taskRepository.save(task)).thenReturn(task);
        when(taskRepository.findAll()).thenReturn(taskList);
        when(taskRepository.listOfTasksByStatus("pending")).thenReturn(taskList);
        when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));
        when(taskRepository.findById(1)).thenReturn(Optional.ofNullable(task));
        when(userRepository.findUserByEmail("enwerevincent@gmail.com")).thenReturn(Optional.of(user));
        when(taskRepository.updateTaskByIdAndStatus("ongoing" , 1)).thenReturn(true);

    }

    @Test
    void registerUser() {
        when(userServiceImpl.registerUser(userDTO)).thenReturn(user);
        var actual = userServiceImpl.registerUser(userDTO);
        var expected = user;
        assertEquals( expected , actual );
    }

    @Test
    void loginUser_Successfull() {
        String message = "Success";
        assertEquals(message , userServiceImpl.loginUser("elemelefelix@gmail.com" , "12345"));
    }

    @Test
    void loginUser_Unsuccessfull() {
        String message = "incorrect password";
        assertEquals(message , userServiceImpl.loginUser("elemelefelix@gmail.com" , "1234"));
    }


    @Test
    void createTask() {
        when(userServiceImpl.createTask(taskDTO)).thenReturn(task);
        assertEquals(task , userServiceImpl.createTask(taskDTO));
    }

    @Test
    void updateTitleAndDescription() {
        assertEquals(task , userServiceImpl.updateTitleAndDescription(taskDTO , 1));
    }

    @Test
    void viewAllTasks() {
        assertEquals(1 , userServiceImpl.viewAllTasks().size());
    }

    @Test
    void viewAllTaskByStatus() {

        assertEquals(taskList , userServiceImpl.viewAllTaskByStatus("pending"));
    }


    @Test
    void updateTaskStatus() {
        assertTrue(userServiceImpl.updateTaskStatus("ongoing" , 1));
    }

    @Test
    void getUserByEmail() {
        assertEquals(user , userServiceImpl.getUserByEmail("elemelefelix@gmail.com"));
    }

    @Test
    void getTaskById() {
        assertEquals(task, userServiceImpl.getTaskById(1));
    }
}