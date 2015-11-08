package by.manager.task.utils.errors.exceptions.aspect;

public enum ServiceAspectError {
    SERVICE_ASPECT_000("blablabla %s");

    private final String value;

    private ServiceAspectError(String s) {
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
