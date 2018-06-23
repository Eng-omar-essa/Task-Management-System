package io.task.serviceImpl;

import io.task.dao.Task;
import io.task.repos.TaskRepository;
import io.task.service.TaskService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class TaskServiceImplTest {
    @Mock
    TaskService taskService;

    @Mock
    TaskRepository taskRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void listAll() {
        Task task1 = new Task();
        Task task2= new Task();
        Task task3 = new Task();
        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        when(taskService.listAll()).thenReturn(tasks);
        assertEquals(tasks.size() ,3);
    }

    @Test
    public void getById() {
        Task task1 = new Task();
        when(taskService.getById(task1.getId())).thenReturn(Optional.ofNullable(task1));
        assertEquals(task1.getId() , null);
    }

    @Test
    public void saveOrUpdate() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void listAllTaskByDueDateAndPriority() {
    }
}