package com.zcxy.utils;

import cn.com.sise.enums.ErrorCode;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author asuna
 */
public class ResponseWrapper<T> implements Serializable {
    private static final long serialVersionUID = 703961951571637826L;
    private T data;
    private boolean success;
    private String errorCode;
    private String message;
    private HttpStatus httpStatus;

    private ResponseWrapper() {
    }

    public static <T> ResponseWrapper<T> success(T data) {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<>();
        responseWrapper.success = true;
        responseWrapper.message = "OK";
        responseWrapper.data = data;
        responseWrapper.httpStatus = HttpStatus.OK;
        return responseWrapper;
    }




    public static <T> ResponseWrapper<T> success() {
        return success(null);
    }

    public static <T> ResponseWrapper<T> fail(ErrorCode errorCode) {
        return fail(errorCode.getCode(), errorCode.getMessage());
    }

    public static <T> ResponseWrapper<T> fail(ErrorCode errorCode, Object... args) {
        return fail(errorCode.getCode(), errorCode.getMessage());
    }

    public static <T> ResponseWrapper<T> fail(String errorCode, String errorMessage) {
        return fail(HttpStatus.OK, errorCode, errorMessage);
    }

    public static <T> ResponseWrapper<T> fail(HttpStatus httpStatus, String errorCode, String errorMessage) {
        return fail(httpStatus, errorCode, errorMessage, null);
    }

    public static <T> ResponseWrapper<T> fail(HttpStatus httpStatus, String errorCode, T data) {
        return fail(httpStatus, errorCode, errorCode, data);
    }

    public static <T> ResponseWrapper<T> fail(HttpStatus httpStatus, String errorCode, String errorMessage, T data) {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<>();
        responseWrapper.success = false;
        responseWrapper.httpStatus = httpStatus;
        responseWrapper.errorCode = errorCode;
        responseWrapper.message = errorMessage;
        responseWrapper.data = data;
        return responseWrapper;
    }

    public T get() {
        return data;
    }

    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public <A> ResponseWrapper<A> map(Function<T, A> func) {
        if (Objects.isNull(this.data)) {
            return ResponseWrapper.success();
        }
        A apply = func.apply(this.data);
        return ResponseWrapper.success(apply);
    }

    public static <T> ResponseWrapper<T> of(T response) {
        if (Objects.isNull(response)) {
            return ResponseWrapper.success();
        }
        return ResponseWrapper.success(response);
    }
}
