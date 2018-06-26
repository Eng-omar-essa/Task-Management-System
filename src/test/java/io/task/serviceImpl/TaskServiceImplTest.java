package io.task.serviceImpl;

import io.task.dao.Enum.TaskStatus;
import io.task.dao.Task;
import io.task.repos.TaskRepository;
import io.task.service.TaskService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.*;

import static org.junit.Assert.*;

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

        Task task1 = getTask();
        Task task2 = getTask();
        Task task3 = getTask();
        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        when(taskService.listAll()).thenReturn(tasks);
        assertEquals(tasks.size() ,3);
    }

    @Test
    public void getById() {
        Task task1 = getTask();
        when(taskService.getById(task1.getId())).thenReturn(Optional.ofNullable(task1));
        assertNotNull(task1);
    }

    @Test
    public void saveOrUpdate() {
        Task task1 = new Task();
        when(taskService.saveOrUpdate(task1)).thenReturn(task1);
        assertNotNull(task1);
    }

    @Test
    public void delete() {
        Task task1 = getTask();
        taskRepository.delete(task1);
        verify(taskRepository, times(1)).delete(task1);

    }

    @Test
    public void listAllTaskByDueDateAndPriority() {
    }

    private Task getTask(){
        final Date createdAt = new Date();
        final Date updatedAt = null;
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE,new Random().nextInt()+1);
        final Date dueDate = c.getTime();
        final String title = "TASK - " ;
        final String description = "This is Sample Task - ";
        final Integer priority = new Random().nextInt(6);
        final TaskStatus taskStatus = TaskStatus.values()[new Random().nextInt(TaskStatus.values().length)];

        Date resolvedAt = null;
        Date reminderAt = null;
        Task task1 = new Task(createdAt,updatedAt,reminderAt,dueDate,title,description,priority,taskStatus,resolvedAt);
        return task1;

    }
}