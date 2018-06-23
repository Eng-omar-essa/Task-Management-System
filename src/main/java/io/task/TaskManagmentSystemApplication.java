package io.task;

import io.task.scheduler.TaskScheduler;
import io.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class TaskManagmentSystemApplication {

    private static TaskService taskService;

    @Autowired
	public TaskManagmentSystemApplication(TaskService taskService){
        TaskManagmentSystemApplication.taskService = taskService;
	}


	/**
	 * Set UTC time zone
	 */
	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	/**
	 * Spring boot application starting point
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(TaskManagmentSystemApplication.class, args);
        new TaskScheduler(taskService).run();
	}
}
