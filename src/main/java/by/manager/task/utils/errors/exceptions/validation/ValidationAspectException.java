package by.manager.task.utils.errors.exceptions.validation;

import by.manager.task.utils.errors.exception.ExtendedThrowable;

public class ValidationAspectException extends ExtendedThrowable {

    private static final long serialVersionUID = 1211061064628668484L;
    private ValidationAspectError code;
    private String codeName;
    private String message;
    private Object[] params;

    public ValidationAspectException() {
        super();
    }

    public ValidationAspectException(Throwable t) {
        super(t);
    }

    public ValidationAspectException(ValidationAspectError code) {
        this.code = code;
        this.message = String.format(code.toString());
    }

    public ValidationAspectException(ExtendedThrowable t, ValidationAspectError code, Object... params) {
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

    public ValidationAspectError getCode() {
        return code;
    }

    public void setCode(ValidationAspectError code) {
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
