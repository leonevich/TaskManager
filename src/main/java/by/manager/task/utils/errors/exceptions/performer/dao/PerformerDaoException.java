package by.manager.task.utils.errors.exceptions.performer.dao;

import by.manager.task.utils.errors.exception.ExtendedThrowable;

public class PerformerDaoException extends ExtendedThrowable {

    private static final long serialVersionUID = -6203990467232770085L;

    private PerformerDaoError code;
    private String codeName;
    private String message;
    private Object[] params;

    public PerformerDaoException() {
        super();
    }

    public PerformerDaoException(Throwable t) {
        super(t);
    }

    public PerformerDaoException(PerformerDaoError code) {
        this.code = code;
        this.message = String.format(code.toString());
    }

    public PerformerDaoException(ExtendedThrowable t, PerformerDaoError code, Object... params) {
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

    public PerformerDaoError getCode() {
        return code;
    }

    public void setCode(PerformerDaoError code) {
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
