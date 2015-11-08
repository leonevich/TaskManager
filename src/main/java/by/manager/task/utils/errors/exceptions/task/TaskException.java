package by.manager.task.utils.errors.exceptions.task;

import by.manager.task.utils.errors.exception.ExtendedThrowable;


public class TaskException extends ExtendedThrowable {

    private static final long serialVersionUID = 8639149601122693436L;
    private TaskError code;
    private String codeName;
    private String message;
    private Object[] params;

    public TaskException() {
        super();
    }

    public TaskException(Throwable t) {
        super(t);
    }

    public TaskException(TaskError code) {
        this.code = code;
        this.message = String.format(code.toString());
    }

    public TaskException(ExtendedThrowable t, TaskError code, Object... params) {
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

    public TaskError getCode() {
        return code;
    }

    public void setCode(TaskError code) {
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
