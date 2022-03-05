package com.mabel.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

/**
 * @project: helper
 * @description:
 * 自定义restTemplate 异常处理器
 * RestTemplate 默认异常处理器是 {@link DefaultResponseErrorHandler}
 * 看这个默认的处理器会发现，只要不是200的状态码都视为错误，直接抛出异常且不返回responseBody,
 * 这在实际使用过程中并不实用，所以参照它自定义一个异常护理器，即使非200的状态码，也正常返回
 * @author: Mabel.Chen
 * @create: 2022-03-05
 **/
public class RestTemplateErrorHandler implements ResponseErrorHandler {

    public boolean hasError(ClientHttpResponse response) throws IOException {
        int rawStatusCode = response.getRawStatusCode();
        HttpStatus statusCode = HttpStatus.resolve(rawStatusCode);
        return statusCode != null ? this.hasError(statusCode) : this.hasError(rawStatusCode);
    }

    protected boolean hasError(HttpStatus statusCode) {
        return statusCode.isError();
    }

    protected boolean hasError(int unknownStatusCode) {
        HttpStatus.Series series = HttpStatus.Series.resolve(unknownStatusCode);
        return series == HttpStatus.Series.CLIENT_ERROR || series == HttpStatus.Series.SERVER_ERROR;
    }

    public void handleError(ClientHttpResponse response) throws IOException {

    }
}