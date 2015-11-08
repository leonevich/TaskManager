package by.manager.task.dao.domain;

import by.manager.task.utils.enums.PerformerStatus;
import by.manager.task.utils.enums.PerformerType;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "PERFORMER")
public class Performer implements Serializable {


    private static final long serialVersionUID = -5316045926545691259L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PERFORMER_ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "TYPE")
    private PerformerType type;
    @Column(name = "STATUS")
    private PerformerStatus status;

    @OneToMany(mappedBy = "performer", cascade = CascadeType.ALL)
    private List<Task> taskList;

    public Performer() {
    }

    public Performer(String name, PerformerType type, PerformerStatus status) {
        this.name = name;
        this.type = type;
        this.status = status;
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

    public PerformerType getType() {
        return type;
    }

    public void setType(PerformerType type) {
        this.type = type;
    }

    public PerformerStatus getStatus() {
        return status;
    }

    public void setStatus(PerformerStatus status) {
        this.status = status;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Performer performer = (Performer) o;

        if (id != null ? !id.equals(performer.id) : performer.id != null) return false;
        if (name != null ? !name.equals(performer.name) : performer.name != null) return false;
        if (type != performer.type) return false;
        return status == performer.status;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Performer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                '}';
    }


}
