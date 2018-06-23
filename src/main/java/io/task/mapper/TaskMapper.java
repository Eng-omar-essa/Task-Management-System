package io.task.mapper;

import io.task.dao.Task;
import io.task.dto.TaskDTO;
import io.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

public class TaskMapper {

    /**
     * Convert Task data transfer object to task entity
     * @param taskDTO
     * @return
     */

    private static TaskService taskService;

    @Autowired
    public TaskMapper(TaskService taskService){
        TaskMapper.taskService = taskService;
    }

    public static Task convertToEntity(TaskDTO taskDTO){

        Task task = null;

        if (taskDTO.getId() != null && taskDTO.getVersion() != null) {
            Optional<Task> oldTask = taskService.getById(taskDTO.getId());

            if(oldTask.isPresent()){
                task = new Task(taskDTO.getId(),taskDTO.getVersion(),taskDTO.getCreatedAt(), taskDTO.getUpdatedAt(), taskDTO.getDueDate(), taskDTO.getResolvedAt(), taskDTO.getTitle(),
                        taskDTO.getDescription(), taskDTO.getPriority(), taskDTO.getTaskStatus(), taskDTO.getReminderAt());
            } else {
                task = new Task(taskDTO.getCreatedAt(), taskDTO.getUpdatedAt(), taskDTO.getDueDate(), taskDTO.getResolvedAt(), taskDTO.getTitle(),
                        taskDTO.getDescription(), taskDTO.getPriority(), taskDTO.getTaskStatus(), taskDTO.getReminderAt());
            }
        }
        else {
            task = new Task(taskDTO.getCreatedAt(), taskDTO.getUpdatedAt(), taskDTO.getDueDate(), taskDTO.getResolvedAt(), taskDTO.getTitle(),
                    taskDTO.getDescription(), taskDTO.getPriority(), taskDTO.getTaskStatus(), taskDTO.getReminderAt());
        }

        return task;

    }

    /**
     * Convert task entity to task data transfer object
     * @param
     * @return
     */
    public static TaskDTO convertToDto(Optional<Task> task_){

        TaskDTO taskDTO = null;

        if(task_.isPresent()){
            Task task  = task_.get();
            if(task.getId() != null && task.getVersion() != null){
                taskDTO = new TaskDTO(task.getId(), task.getVersion(), task.getCreatedAt(), task.getUpdatedAt(),
                        task.getDueDate(), task.getResolvedAt(), task.getTitle(), task.getDescription(), task.getPriority(), task.getTaskStatus(), task.getReminderAt());
            }
            else {
                taskDTO = new TaskDTO(null, null, task.getCreatedAt(), task.getUpdatedAt(),
                        task.getDueDate(), task.getResolvedAt(), task.getTitle(), task.getDescription(), task.getPriority(), task.getTaskStatus(), task.getReminderAt());
            }
        }


        return taskDTO;

    }

}
