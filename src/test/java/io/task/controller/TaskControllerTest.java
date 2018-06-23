package io.task.controller;

import io.task.dao.Task;
import io.task.service.TaskService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class TaskControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(taskController)
                .build();
    }


    @Test
    public void listAllTasks() throws Exception {
        Task task1 = new Task();
        Task task2= new Task();
        Task task3 = new Task();
        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        when(taskService.listAll()).thenReturn(tasks);
        mockMvc.perform(get("/api/v1/tasks")).andExpect(status().isNoContent());
//                .andExpect(tasks);

        verify(taskService).listAll();
    }

    @Test
    public void getTask() {
    }

    @Test
    public void createTask() {
    }

    @Test
    public void updateTask() {
    }

    @Test
    public void deleteTask() {
    }
}