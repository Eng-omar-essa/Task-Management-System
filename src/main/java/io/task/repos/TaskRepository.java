package io.task.repos;

import java.util.UUID;
import java.util.stream.Stream;

import io.task.dao.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;



public interface TaskRepository extends CrudRepository<Task,UUID> {
	
	/**
	 * Get the list of task according to the task due_date and task priority
	 * @return
	 */
	@Query("SELECT t FROM Task t ORDER BY due_date ASC , priority DESC")
	public Stream<Task> listAllTaskByDueDateAndPriority();
	
}
