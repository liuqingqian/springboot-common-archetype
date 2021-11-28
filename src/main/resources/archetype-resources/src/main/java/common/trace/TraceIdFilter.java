package ${package}.common.trace;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class TraceIdFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        String traceId = servletRequest.getHeader(TraceIdUtil.TRACE_ID);
        if(StringUtils.isBlank(traceId)){
            traceId = TraceIdUtil.traceId();
        }
        MDC.put("traceId", traceId);
        MDC.put("reqUrl", servletRequest.getRequestURI());
        MDC.put("reqMethod", servletRequest.getMethod());
        MDC.put("reqParams", JSON.toJSONString(request.getParameterMap()));
        chain.doFilter(request, response);
        MDC.clear();
    }

    @Override
    public void destroy() {}
}
