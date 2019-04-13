package com.mabel;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mabel.pojo.form.user.LoginForm;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.util.Base64Utils;

import javax.servlet.http.HttpServletRequest;

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
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isBlank(authorization) || !this.checkToken(authorization)) {
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            context.getResponse().setContentType("text/html;charset=utf-8");
            context.setResponseBody("请先登录系统");
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
        } else if (requestURI.endsWith("/register")) {
            return true;
        } else if (requestURI.endsWith("/login")) {
            return true;
        }
        return false;
    }

    public static boolean checkToken(String token) {
        if (StringUtils.isBlank(token)) {
            return false;
        }
        DecodedJWT decode = JWT.decode(token);
        byte[] bytes = Base64Utils.decodeFromString(decode.getPayload());
        String payload = new String(bytes);
        if (StringUtils.isNotBlank(payload) && payload.contains("com.mabel.Helper.Secret")) {
            return true;
        }
        return false;
    }
}