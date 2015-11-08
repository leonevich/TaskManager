package by.manager.task.utils.errors.exceptions.task.service;

import by.manager.task.utils.errors.exception.ExtendedThrowable;

public class TaskServiceException extends ExtendedThrowable {

    private static final long serialVersionUID = 9166262314950526335L;
    private TaskServiceError code;
    private String codeName;
    private String message;
    private Object[] params;

    public TaskServiceException() {
        super();
    }

    public TaskServiceException(Throwable t) {
        super(t);
    }

    public TaskServiceException(TaskServiceError code) {
        this.code = code;
        this.message = String.format(code.toString());
    }

    public TaskServiceException(ExtendedThrowable t, TaskServiceError code, Object... params) {
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

    public TaskServiceError getCode() {
        return code;
    }

    public void setCode(TaskServiceError code) {
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
