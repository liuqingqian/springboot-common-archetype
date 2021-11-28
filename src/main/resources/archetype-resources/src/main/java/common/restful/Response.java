package ${package}.common.restful;

import lombok.Data;

@Data
public class Response<T> {
    private int errorCode;
    private String errorMsg;
    private long stime = System.currentTimeMillis();
    private T results;

    Response() {
    }

    public static <T> ResponseBuilder<T> builder() {
        return ResponseBuilder.create();
    }

    public static <T> Response<T> ok() {
        return ok(null);
    }

    public static <T> Response<T> ok(T results) {
        return of(ErrorCode.OK, "ok", results);
    }

    public static <T> Response<T> error(ErrorCode errorCode) {
        return of(errorCode, errorCode.getMsg());
    }

    public static <T> Response<T> error(ErrorCode errorCode, T results) {
        return of(errorCode, errorCode.getMsg(), results);
    }

    public static <T> Response<T> error(int errorCode, String errorMsg) {
        return of(errorCode, System.currentTimeMillis(), errorMsg, null);
    }

    public static <T> Response<T> error(ErrorCode errorCode, String errorMsg) {
        return of(errorCode, errorMsg, null);
    }

    public static <T> Response<T> error(ErrorCode errorCode, String errorMsg, T results) {
        return of(errorCode, errorMsg, results);
    }

    public static <T> Response<T> of(ErrorCode errorCode) {
        return of(errorCode, errorCode.getMsg());
    }

    public static <T> Response<T> of(ErrorCode errorCode, T results) {
        return of(errorCode, errorCode.getMsg(), results);
    }

    public static <T> Response<T> of(ErrorCode errorCode, String errorMsg) {
        return of(errorCode, errorMsg, null);
    }

    public static <T> Response<T> of(ErrorCode errorCode, String errorMsg, T results) {
        return of(errorCode, System.currentTimeMillis(), errorMsg, results);
    }

    public static <T> Response<T> of(ErrorCode errorCode, long stime, String errorMsg, T results) {
        Response<T> resp = new Response<>();
        resp.setResults(results);
        resp.setErrorCode(errorCode.getCode());
        if (null != errorMsg) {
            resp.setErrorMsg(errorMsg);
        }
        resp.setStime(stime);
        return resp;
    }

    public static <T> Response<T> of(int errorCode, long stime, String errorMsg, T results) {
        Response<T> resp = new Response<>();
        resp.setResults(results);
        resp.setErrorCode(errorCode);
        if (null != errorMsg) {
            resp.setErrorMsg(errorMsg);
        }
        resp.setStime(stime);
        return resp;
    }

    public static <T> boolean isSuccess(Response<T> response) {
        return response != null && response.getErrorCode() == 0;
    }

}

