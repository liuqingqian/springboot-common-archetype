package ${package}.common.restful;

public class ErrorCode {
    public static final ErrorCode OK = define(0, "ok");
    public static final ErrorCode PARAM_INVALID = define(-100, "参数错误");
    public static final ErrorCode PATH_NOT_FOUND = define(-102, "路由不存在");
    public static final ErrorCode BUSI_VALIDATE_ERROR = define(-103, "业务规则验证失败");
    public static final ErrorCode REPEATED_SUBMIT_ERROR = define(-104, "请勿重复提交");
    public static final ErrorCode PERMISSION_DENIED = define(-105, "无权访问");
    public static final ErrorCode UNLOGIN_EXCEPTION = define(-106, "未登录，请重新登录");
    public static final ErrorCode UNBOUND_ACCOUNT = define(-107, "未绑定账号");
    public static final ErrorCode BOUND_ACCOUNT_FAIL = define(-108, "账号绑定失败");
    public static final ErrorCode REPEAT_BOUND_ACCOUNT = define(-109, "重复绑定账号");
    public static final ErrorCode DB_EXCEPTION = define(-200, "数据库错误");
    public static final ErrorCode REDIS_EXCEPTION = define(-300, "Redis错误");
    public static final ErrorCode MQ_EXCEPTION = define(-400, "MQ错误");
    public static final ErrorCode RPC_EXCEPTION = define(-500, "RPC调用错误");
    public static final ErrorCode CONFIG_EXCEPTION = define(-600, "配置错误");
    public static final ErrorCode UNKNOWN_EXCEPTION = define(-700, "未知错误");

    public static final ErrorCode TOKEN_INVALID = define(30004, "token格式错误");
    public static final ErrorCode TOKEN_EXPIRED = define(30005, "token已过期");
    public static final ErrorCode TOKEN_KICK_OUT = define(30017, "互踢，强制下线");

    private int code;
    private String msg;

    protected ErrorCode() {
        this.code = 0;
        this.msg = null;
    }

    private ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ErrorCode define(int code, String msg) {
        return new ErrorCode(code, msg);
    }

    public static ErrorCode define(int code) {
        return define(code, (String) null);
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
