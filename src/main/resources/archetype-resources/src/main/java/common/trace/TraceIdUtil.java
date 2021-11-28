package ${package}.common.trace;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.core.task.TaskDecorator;

import java.util.Map;
import java.util.UUID;

public class TraceIdUtil {

    public static final String TRACE_ID = "TRACE-ID";

    public static String traceId() {
        String traceId = MDC.get("traceId");
        if(StringUtils.isBlank(traceId)){
            traceId = UUID.randomUUID().toString().replaceAll("-", "");
        }
        return traceId;
    }


    public static FilterRegistrationBean traceIdFilter() {
        FilterRegistrationBean<TraceIdFilter> registration = new FilterRegistrationBean<>();
        TraceIdFilter traceIdFilter = new TraceIdFilter();
        registration.setFilter(traceIdFilter);
        registration.addUrlPatterns("/*");
        registration.setName("traceIdFilter");
        return registration;
    }

    public static TaskDecorator mdcTaskDecorator = (runnable) -> {
        Map<String,String> map = MDC.getCopyOfContextMap();
        return () -> {
            try{
                MDC.setContextMap(map);
                runnable.run();
            } finally {
                MDC.clear();
            }
        };
    };

}
