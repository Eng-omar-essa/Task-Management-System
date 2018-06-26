package io.task.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.task.dao.Enum.TaskStatus;
import io.task.dao.Task;
import io.task.service.TaskService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;


@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
//@ContextConfiguration
//@SpringBootTest

public class TaskControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

//    @Autowired
//    private WebApplicationContext wac;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    @Test
    public void listAllTasks() throws Exception {
//        List<Task> tasks = JsonPath.read("listall.json", Task.class);
//
//        try{
//            ObjectMapper mapper = new ObjectMapper();
//
//            JsonNode expectedNode = mapper.readTree(tasks.jsonString());
//            System.out.println(expectedNode.toString());
//        }catch (Exception e){
//            throw new RuntimeException(e);
//        }


//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tasks").accept(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$[0]"), hasSize(11))).andDo(print());
    }

    @Test
    public void createTask() {
//        this.mockMvc.perform(post("/api/v1/tasks")
//                .contentType(APPLICATION_JSON)
//                .content(organizationPayload("org1"))
//                .accept(APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isCreated());

    }





    @Test
    public void updateTask() {
    }

    @Test
    public void deleteTask() {
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