package by.manager.task.utils.beans;

import by.manager.task.utils.enums.TaskStatus;
import by.manager.task.utils.enums.TaskType;

import java.io.Serializable;
import java.sql.Timestamp;

public class TaskVO implements Serializable {


    private static final long serialVersionUID = -3906199771297384636L;
    private Long id;
    private String name;
    private Timestamp creationTime;
    private TaskStatus status;
    private TaskType type;
    private Integer priority;

    public TaskVO() {
    }

    public TaskVO(String name, TaskStatus status, TaskType type, Integer priority) {
        this.name = name;
        this.status = status;
        this.type = type;
        this.priority = priority;
    }

    public TaskVO(Long id, String name, Timestamp creationTime, TaskStatus status, TaskType type, Integer priority) {
        this.id = id;
        this.name = name;
        this.creationTime = creationTime;
        this.status = status;
        this.type = type;
        this.priority = priority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskVO taskVO = (TaskVO) o;

        if (id != null ? !id.equals(taskVO.id) : taskVO.id != null) return false;
        if (name != null ? !name.equals(taskVO.name) : taskVO.name != null) return false;
        if (creationTime != null ? !creationTime.equals(taskVO.creationTime) : taskVO.creationTime != null)
            return false;
        if (status != taskVO.status) return false;
        if (type != taskVO.type) return false;
        return !(priority != null ? !priority.equals(taskVO.priority) : taskVO.priority != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (creationTime != null ? creationTime.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (priority != null ? priority.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TaskVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creationTime=" + creationTime +
                ", status=" + status +
                ", type=" + type +
                ", priority=" + priority +
                '}';
    }
}
