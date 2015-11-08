package by.manager.task.utils.errors.exceptions.task.dao;


import by.manager.task.utils.errors.exception.ExtendedThrowable;

public class TaskDaoException extends ExtendedThrowable {

    private static final long serialVersionUID = -8775081234132893373L;
    private TaskDaoError code;
    private String codeName;
    private String message;
    private Object[] params;

    public TaskDaoException() {
        super();
    }

    public TaskDaoException(Throwable t) {
        super(t);
    }

    public TaskDaoException(TaskDaoError code) {
        this.code = code;
        this.message = String.format(code.toString());
    }

    public TaskDaoException(ExtendedThrowable t, TaskDaoError code, Object... params) {
        super(t);
        if (t.getMessage() == null) {
            this.message = String.format(code.toString(), params);
            this.codeName = code.name();
            this.params = params;
        } else {
            this.message = t.getMessage();
            this.codeName = t.getCodeName();
            this.params = t.getParams();
        }
    }

    public TaskDaoError getCode() {
        return code;
    }

    public void setCode(TaskDaoError code) {
        this.code = code;
    }

    @Override
    public String getCodeName() {
        return codeName;
    }

    @Override
    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Object[] getParams() {
        return params;
    }

    @Override
    public void setParams(Object[] params) {
        this.params = params;
    }
}
