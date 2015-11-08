package by.manager.task.utils.errors.exceptions.dao;


public enum DaoError {
    BASE_DAO_000("blablabla %s");

    private final String value;

    private DaoError(String s) {
        value = s;
    }

    public boolean equalsValue(String value2) {
        return (value2 != null) && value.equals(value2);
    }

    @Override
    public String toString() {
        return value;
    }
}
