package by.manager.task.utils.beans;


import by.manager.task.utils.enums.PerformerStatus;
import by.manager.task.utils.enums.PerformerType;

import java.io.Serializable;

public class PerformerVO implements Serializable {

    private static final long serialVersionUID = 4467225547408472543L;
    private Long id;
    private String name;
    private PerformerType type;
    private PerformerStatus status;

    public PerformerVO() {
    }

    public PerformerVO(String name, PerformerType type, PerformerStatus status) {
        this.name = name;
        this.type = type;
        this.status = status;
    }

    public PerformerVO(Long id, String name, PerformerType type, PerformerStatus status) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PerformerVO that = (PerformerVO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (type != that.type) return false;
        return status == that.status;

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
        return "PerformerVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", status=" + status +
                '}';
    }
}
