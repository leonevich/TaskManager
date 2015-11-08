package by.manager.task.utils.errors.exceptions.executor.read;

import by.manager.task.utils.errors.exception.ExtendedThrowable;

import java.io.FileNotFoundException;


public class ReadExecutorException extends ExtendedThrowable {

    private static final long serialVersionUID = 5628217172007802767L;
    private ReadExecutorError code;
    private String codeName;
    private String message;
    private Object[] params;

    public ReadExecutorException() {
        super();
    }

    public ReadExecutorException(Throwable t) {
        super(t);
    }

    public ReadExecutorException(ReadExecutorError code) {
        this.code = code;
        this.message = String.format(code.toString());
    }

    public ReadExecutorException(ExtendedThrowable t, ReadExecutorError code, Object... params) {
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

    public ReadExecutorError getCode() {
        return code;
    }

    public void setCode(ReadExecutorError code) {
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
