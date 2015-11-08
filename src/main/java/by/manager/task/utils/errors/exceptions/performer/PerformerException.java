package by.manager.task.utils.errors.exceptions.performer;

import by.manager.task.utils.errors.exception.ExtendedThrowable;
import by.manager.task.utils.errors.exceptions.performer.service.PerformerServiceException;


public class PerformerException extends ExtendedThrowable {

    private static final long serialVersionUID = 152325229065249203L;
    private PerformerError code;
    private String codeName;
    private String message;
    private Object[] params;

    public PerformerException() {
        super();
    }

    public PerformerException(Throwable t) {
        super(t);
    }

    public PerformerException(PerformerError code) {
        this.code = code;
        this.message = String.format(code.toString());
    }

    public PerformerException(ExtendedThrowable t, PerformerError code, Object... params) {
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

    public PerformerError getCode() {
        return code;
    }

    public void setCode(PerformerError code) {
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
