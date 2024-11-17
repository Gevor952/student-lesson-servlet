package am.itspace.studentlessonservlet1.exception;

public class NotFindLessonsException extends RuntimeException {
    public NotFindLessonsException() {
    }

    public NotFindLessonsException(String message) {
        super(message);
    }

    public NotFindLessonsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFindLessonsException(Throwable cause) {
        super(cause);
    }

    public NotFindLessonsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
