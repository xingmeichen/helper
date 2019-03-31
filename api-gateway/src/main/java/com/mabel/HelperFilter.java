package com.mabel;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

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

    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        InputStream stream = context.getResponseDataStream();
        String body = null;
        try {
            body = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
            context.setResponseBody(body);
        } catch (IOException e) {
            return null;
        }
        return null;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }
}