package by.manager.task.dao.domain;

import by.manager.task.utils.enums.TaskStatus;
import by.manager.task.utils.enums.TaskType;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "TASK")
public class Task implements Serializable {


    private static final long serialVersionUID = 228322260526717200L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TASK_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CREATION_TIME")
    private Timestamp creationTime;

    @Column(name = "STATUS")
    private TaskStatus status;

    @Column(name = "TYPE")
    private TaskType type;

    @Column(name = "PRIORITY")
    private Integer priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERFORMER_ID")
    private Performer performer;

    public Task() {
    }

    public Task(String name, Timestamp creationTime, TaskStatus status, TaskType type, Integer priority, Performer performer) {
        this.name = name;
        this.creationTime = creationTime;
        this.status = status;
        this.type = type;
        this.priority = priority;
        this.performer = performer;
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

    public Performer getPerformer() {
        return performer;
    }

    public void setPerformer(Performer performer) {
        this.performer = performer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (id != null ? !id.equals(task.id) : task.id != null) return false;
        if (name != null ? !name.equals(task.name) : task.name != null) return false;
        if (creationTime != null ? !creationTime.equals(task.creationTime) : task.creationTime != null) return false;
        if (status != task.status) return false;
        if (type != task.type) return false;
        return !(priority != null ? !priority.equals(task.priority) : task.priority != null);

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
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creationTime=" + creationTime +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", priority=" + priority +
                '}';
    }
}
