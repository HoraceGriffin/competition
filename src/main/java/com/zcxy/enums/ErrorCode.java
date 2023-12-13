package com.zcxy.enums;



/*enums 是枚举包*/
public enum ErrorCode implements com.zcxy.enums.constant.ErrorCode {
    CREATE_FAILED("CREATE_FAILED", "创建失败."),
    UPDATE_FAILED("UPDATE_FAILED", "更新失败."),
    DELETE_FAILED("DELETE_FAILED","删除失败0"),
    PARAM_INVALID_ERROR("PARAM_INVALID_ERROR", "参数不合法."),
    DATA_NOT_EXIST("DATA_NOT_EXIST", "目标数据不存在."),
    UNKNOWN_EXCEPTION_FOR_CLIENT("UNKNOWN_EXCEPTION_FOR_CLIENT", "未知异常."),
    ;

    private String code;

    private String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
