package com.zcxy.exception;



import com.zcxy.enums.ErrorCode;
import org.springframework.http.HttpStatus;

/*exception 是自己自定义的异常信息，可以在里面手动抛出异常*/
public class DBException extends RuntimeException {

   private final ErrorCode errorCode;

   private final HttpStatus httpStatus;

    public DBException(ErrorCode errorCode) {
        this(errorCode.getMessage(), errorCode, HttpStatus.BAD_REQUEST);
    }

    public DBException(String message, ErrorCode errorCode, HttpStatus httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }
    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
