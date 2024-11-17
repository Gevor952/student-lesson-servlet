package am.itspace.studentlessonservlet1.exception;

public class NotFindStudentsException extends RuntimeException {
    public NotFindStudentsException(String message) {
        super(message);
    }

    public NotFindStudentsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFindStudentsException(Throwable cause) {
        super(cause);
    }

    public NotFindStudentsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public NotFindStudentsException() {
    }
}
