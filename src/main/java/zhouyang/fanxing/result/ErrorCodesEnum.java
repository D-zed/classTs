package zhouyang.fanxing.result;

public enum  ErrorCodesEnum {
    /**
     * 框架类公共错误
     */
    PARAMS_VALIAD_ERROR("MC9999","请求参数校验错误"),
    REQUEST_METHOD_ERROR("MC9998","请求方式错误"),
    PARAM_TYPE_TRANSFORM_ERROR("MC9997","参数类型转换错误"),
    ILLEGAL_ARGUMENT("MC9996","参数非法"),
    REQUEST_BIND_ERROR("MC9995","参数绑定错误"),
    DATA_BIND_ERROR("MC9994","数据映射错误"),
    FRAMEWORK_INIT_ERROR("MC9993","框架初始化错误"),
    NOT_SUPPORTED_SEND_WAY("MC9992","不支持的发送方式"),
    ;



    private String code;
    private String message;

    ErrorCodesEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ErrorCodesEnum getErrorCodeEnum(String code) {
        for (ErrorCodesEnum item : ErrorCodesEnum.values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }

        return null;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}