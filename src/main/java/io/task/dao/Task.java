package io.task.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.task.dao.Enum.TaskStatus;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
public final class Task extends BaseEntity implements Serializable {


    private static final long serialVersionUID = -6954935371244985098L;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private final Date createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private final Date updatedAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private final Date dueDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private final Date resolvedAt;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private final Date reminderAt;

    @Size(min =1,message = "title should have at least 2 character")
    private final String title;

//    @Size(min =5,message = "title should have at least 2 character")
    private final String description;

    private final Integer priority;

    private final TaskStatus taskStatus;

    public Task() {
        super();
        this.createdAt = new Date();
        this.updatedAt = null;
        this.dueDate = new Date();
        this.resolvedAt = null;
        this.title = "";
        this.description = "";
        this.priority = 0;
        this.taskStatus = TaskStatus.ACTIVE;
        this.reminderAt = null;
    }
    
    public Task(Date createdAt, Date updatedAt, Date dueDate, Date resolvedAt,
            String title, String description, Integer priority, TaskStatus taskStatus,Date reminderAt) {
    	super();
	    this.createdAt = createdAt;
	    this.updatedAt = updatedAt;
	    this.dueDate = dueDate;
	    this.resolvedAt = resolvedAt;
	    this.title = title;
	    this.description = description;
	    this.priority = priority;
	    this.taskStatus = taskStatus;
	    this.reminderAt = reminderAt;
    }

    public Task(UUID id,Long version,Date createdAt, Date updatedAt, Date dueDate, Date resolvedAt,
                String title, String description, Integer priority, TaskStatus taskStatus,Date reminderAt) {
    	super(id,version);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.dueDate = dueDate;
        this.resolvedAt = resolvedAt;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.taskStatus = taskStatus;
        this.reminderAt = reminderAt;
    }



    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Date getResolvedAt() {
        return resolvedAt;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPriority() {
        return priority;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }
    
    public Date getReminderAt() {
		return reminderAt;
	}
    
    @Override
    public UUID getId() {
        return super.getId();
    }
    
    @Override
    public Long getVersion() {
        return super.getVersion();
    }

	@Override
	public String toString() {
		return "Task [id=" + getId() +"version=" + getVersion() +"createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", dueDate=" + dueDate + ", resolvedAt="
				+ resolvedAt + ", reminderAt=" + reminderAt + ", title=" + title + ", description=" + description
				+ ", priority=" + priority + ", taskStatus=" + taskStatus + "]";
	}

}
