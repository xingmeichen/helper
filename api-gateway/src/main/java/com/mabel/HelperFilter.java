package com.mabel;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @project: helper
 * @description:
 * @author: Mabel.Chen
 * @create: 2019-03-31 10:47
 **/
public class HelperFilter extends ZuulFilter {

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        if (this.notFilter(request)) {
            context.setSendZuulResponse(true);
            context.setResponseStatusCode(HttpStatus.OK.value());
            return null;
        }
//        InputStream stream = context.getResponseDataStream();
//        String body = null;
//        try {
//            body = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
//            context.setResponseBody(body);
//        } catch (IOException e) {
//            return null;
//        }
        context.setSendZuulResponse(true);
        context.setResponseStatusCode(HttpStatus.OK.value());
        return null;
    }

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.DEBUG_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    public boolean notFilter(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        if (requestURI.endsWith("/v2/api-docs")) {
            return true;
        }
        return false;
    }
}