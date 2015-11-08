package by.manager.task.utils.errors.exceptions.executor.write;

import by.manager.task.utils.errors.exception.ExtendedThrowable;


public class WriteExecutorException extends ExtendedThrowable {

    private static final long serialVersionUID = 7376007107964525802L;
    private WriteExecutorError code;
    private String codeName;
    private String message;
    private Object[] params;

    public WriteExecutorException() {
        super();
    }

    public WriteExecutorException(Throwable t) {
        super(t);
    }

    public WriteExecutorException(WriteExecutorError code) {
        this.code = code;
        this.message = String.format(code.toString());
    }

    public WriteExecutorException(ExtendedThrowable t, WriteExecutorError code, Object... params) {
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

    public WriteExecutorError getCode() {
        return code;
    }

    public void setCode(WriteExecutorError code) {
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
