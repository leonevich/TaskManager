package by.manager.task.utils.errors.exception;

public class ExtendedThrowable extends Throwable {

    private static final long serialVersionUID = 7286159182785633212L;

    private String codeName;
    private String message;
    private Object[] params;

    public ExtendedThrowable() {
        super();
    }

    public ExtendedThrowable(String codeName, String message, Object[] params) {
        super();
        this.codeName = codeName;
        this.message = message;
        this.params = params;
    }

    public ExtendedThrowable(Throwable t) {
        super(t);
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

}
