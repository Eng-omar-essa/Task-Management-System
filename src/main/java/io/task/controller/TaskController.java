package io.task.controller;

import io.task.dao.Enum.TaskStatus;
import io.task.dao.Task;
import io.task.dto.TaskDTO;
import io.task.exception.CustomizedResponseEntityExceptionHandler;
import io.task.exception.TaskNotFoundException;
import io.task.service.TaskService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static io.task.mapper.TaskMapper.convertToDto;
import static io.task.mapper.TaskMapper.convertToEntity;

@RestController
@RequestMapping("/api/v1")
public class TaskController {
    private static final Logger logger = LogManager.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public ResponseEntity<List<TaskDTO>> listAllTasks() {

        logger.info("IN listAllTasks METHOD");

        List<Task> tasks = taskService.listAllTaskByDueDateAndPriority();

        long currentTimeStamp = new Date().getTime();

        tasks.removeIf(task -> (task.getReminderAt() != null && task.getReminderAt() != null && task.getReminderAt().getTime() > currentTimeStamp &&
                task.getTaskStatus().equals(TaskStatus.POSTPONED)));

        if (tasks.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<TaskDTO>>(tasks.stream()
                .map(t -> convertToDto(Optional.ofNullable(t)))
                .collect(Collectors.toList()), HttpStatus.OK);

    }


    @RequestMapping(value = "/tasks/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<?> getTask(@Valid @PathVariable("uuid") UUID uuid) {

        logger.info("IN getTask METHOD");

        Optional<Task> task = taskService.getById(uuid);

        if (!task.isPresent()) {
            throw new TaskNotFoundException("uuid");
        }

        return new ResponseEntity<TaskDTO>(convertToDto(task), HttpStatus.OK);

    }

    @RequestMapping(value = "/tasks", method = RequestMethod.POST)
     public ResponseEntity<?> createTask(@Valid @RequestBody TaskDTO taskDTO, UriComponentsBuilder ucBuilder, Errors errors) {

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        Task task = taskService.saveOrUpdate(convertToEntity(taskDTO));
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/tasks/{uuid}").buildAndExpand(task.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);

    }

    @RequestMapping(value = "/tasks/{uuid}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateTask(@Valid @PathVariable("uuid") UUID uuid, @Valid @RequestBody TaskDTO taskDTO, Errors errors) throws TaskNotFoundException {

        ResponseEntity<?> responseEntity = null;
        logger.info("IN updateTask METHOD");

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Task> currentTask = taskService.getById(uuid);

        if (currentTask.isPresent() != true) {
            throw new TaskNotFoundException("uuid");

        }


        try{
            Task taskNew = convertToEntity(taskDTO);
            System.out.println(taskNew);
            Task taskSaved = taskService.saveOrUpdate(taskNew);
            Optional<Task> updatedTask = taskService.getById(uuid);
            if (updatedTask.isPresent()) {
                responseEntity = new ResponseEntity<TaskDTO>(convertToDto(updatedTask), HttpStatus.OK);
            } else {
                return ResponseEntity.unprocessableEntity().build();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return  responseEntity;

    }

    @RequestMapping(value = "/tasks/{uuid}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTask(@Valid @PathVariable("uuid") UUID uuid) {
        taskService.delete(uuid);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
