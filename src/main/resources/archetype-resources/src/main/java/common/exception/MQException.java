package ${package}.common.exception;


public class MQException extends RuntimeException {

    public MQException() {
    }

    public MQException(String msg) {
        super(msg);
    }
}
