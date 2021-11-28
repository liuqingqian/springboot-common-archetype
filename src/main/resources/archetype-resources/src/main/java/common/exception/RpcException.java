package ${package}.common.exception;

public class RpcException extends RuntimeException{

    public RpcException(){}

    public RpcException(String msg) {
        super(msg);
    }

}
