package io.task.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import io.task.dao.Task;
import io.task.repos.TaskRepository;
import io.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("taskService")
public class TaskServiceImpl implements TaskService {

    @Autowired
    public TaskRepository taskRepository;


    @Transactional
    @Override
    public List<Task> listAll() {
        List<Task> tasks = new ArrayList<>();
        taskRepository.findAll().forEach(tasks::add);
        return tasks;
    }

    @Transactional
    @Override
    public Optional<Task> getById(UUID id) {
        return  taskRepository.findById(id);
	    }

    @Transactional
    @Override
    public Task saveOrUpdate(Task task) {
        taskRepository.save(task);
        return task;
    }


    @Transactional
    @Override
    public void delete(UUID id) {
        taskRepository.deleteById(id);
    }

    @Transactional
	@Override
	public List<Task> listAllTaskByDueDateAndPriority() {
		return taskRepository.listAllTaskByDueDateAndPriority().collect(Collectors.toCollection(ArrayList::new));
	}

}
