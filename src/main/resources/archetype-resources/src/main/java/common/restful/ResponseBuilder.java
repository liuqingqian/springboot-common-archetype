package ${package}.common.restful;

public final class ResponseBuilder<T> {
    private Response<T> response;

    private ResponseBuilder(Response<T> response) {
        this.response = response;
    }

    public static <T> ResponseBuilder<T> create(){
        return new ResponseBuilder<>(new Response<>());
    }

    public ResponseBuilder<T> stime(long timestamp) {
        this.response.setStime(timestamp);
        return this;
    }

    public ResponseBuilder<T> errorMsg(String errorMsg) {
        this.response.setErrorMsg(errorMsg);
        return this;
    }

    public ResponseBuilder<T> errorCode(ErrorCode errorCode) {
        this.response.setErrorCode(errorCode.getCode());
        return this;
    }

    public ResponseBuilder<T> results(T results) {
        this.response.setResults(results);
        return this;
    }

    public Response<T> build() {
        return this.response;
    }
}
