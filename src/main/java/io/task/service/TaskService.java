package io.task.service;

import io.task.dao.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface TaskService {

    List<Task> listAll();

    Optional<Task> getById(UUID id);

    Task saveOrUpdate(Task task);

    void delete(UUID id);
    
    List<Task> listAllTaskByDueDateAndPriority();

}
