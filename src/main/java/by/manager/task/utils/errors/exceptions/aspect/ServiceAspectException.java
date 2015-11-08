package by.manager.task.utils.errors.exceptions.aspect;


import by.manager.task.utils.errors.exception.ExtendedThrowable;

public class ServiceAspectException extends ExtendedThrowable {

    private ServiceAspectError code;
    private String codeName;
    private String message;
    private Object[] params;

    public ServiceAspectException() {
        super();
    }

    public ServiceAspectException(Throwable t) {
        super(t);
    }

    public ServiceAspectException(ServiceAspectError code) {
        this.code = code;
        this.message = String.format(code.toString());
    }

    public ServiceAspectException(ExtendedThrowable t, ServiceAspectError code, Object... params) {
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

    public ServiceAspectError getCode() {
        return code;
    }

    public void setCode(ServiceAspectError code) {
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
