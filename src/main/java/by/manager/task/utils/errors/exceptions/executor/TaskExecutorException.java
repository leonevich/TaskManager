package by.manager.task.utils.errors.exceptions.executor;

import by.manager.task.utils.errors.exception.ExtendedThrowable;


public class TaskExecutorException extends ExtendedThrowable {
    private static final long serialVersionUID = -365123797964406822L;
    private TaskExecutorError code;
    private String codeName;
    private String message;
    private Object[] params;

    public TaskExecutorException(){
        super();
    }

    public TaskExecutorException(Throwable t) {
        super(t);
    }

    public TaskExecutorException(TaskExecutorError code) {
        this.code = code;
        this.message = String.format(code.toString());
    }

    public TaskExecutorException(ExtendedThrowable t, TaskExecutorError code,Object... params) {
        super(t);
        if (t.getMessage() == null ) {
            this.message = String.format(code.toString(), params);
            this.codeName = code.name();
            this.params = params;
        } else {
            this.message = t.getMessage();
            this.codeName = t.getCodeName();
            this.params = t.getParams();
        }
    }

    public TaskExecutorError getCode() {
        return code;
    }

    public void setCode(TaskExecutorError code) {
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
