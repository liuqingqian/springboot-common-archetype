package ${package}.common.exception;

public class RedisException extends RuntimeException{

    public RedisException(){}

    public RedisException(String msg) {
        super(msg);
    }

}
