package by.manager.task.utils.errors.exceptions.dao;


import by.manager.task.utils.errors.exception.ExtendedThrowable;

public class DaoException extends ExtendedThrowable {

    private static final long serialVersionUID = 5176100368540478478L;
    private DaoError code;
    private String codeName;
    private String message;
    private Object[] params;

    public DaoException(){
        super();
    }

    public DaoException(Throwable t) {
        super(t);
    }

    public DaoException(DaoError code) {
        this.code = code;
        this.message = String.format(code.toString());
    }

    public DaoException(ExtendedThrowable t, DaoError code,Object... params) {
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

    public DaoError getCode() {
        return code;
    }

    public void setCode(DaoError code) {
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
