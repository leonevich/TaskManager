package by.manager.task.utils.errors.exceptions.performer.service;

import by.manager.task.utils.errors.exception.ExtendedThrowable;

public class PerformerServiceException extends ExtendedThrowable {

    private static final long serialVersionUID = -3851860515774812257L;
    private PerformerServiceError code;
    private String codeName;
    private String message;
    private Object[] params;

    public PerformerServiceException() {
        super();
    }

    public PerformerServiceException(Throwable t) {
        super(t);
    }

    public PerformerServiceException(PerformerServiceError code) {
        this.code = code;
        this.message = String.format(code.toString());
    }

    public PerformerServiceException(ExtendedThrowable t, PerformerServiceError code, Object... params) {
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

    public PerformerServiceError getCode() {
        return code;
    }

    public void setCode(PerformerServiceError code) {
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
