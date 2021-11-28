package ${package}.common.exception;

public class RepeatedSubmitException extends RuntimeException {
    public RepeatedSubmitException() {
    }

    public RepeatedSubmitException(String msg) {
        super(msg);
    }
}
