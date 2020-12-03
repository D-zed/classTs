package zhouyang.fanxing;



/**
 * @author chengchen
 * @version 1.0
 * @date 2020/5/14 19:24
 */
public class GlobalResultUtil<T> {

    public static Result<Boolean> fail(String message) {
        return Result.failure().code(GlobalConstants.RESULT_CODE_FAILURE).message(message).build();
    }

    public static <T> Result<T> fail(ErrorCodesEnum errorCodesEnum) {
        return Result.failure().code(errorCodesEnum.getCode()).message(errorCodesEnum.getMessage()).build();
    }

    public static <T> Result<T> fail(String code,String message) {
        return Result.failure().code(code).message(message).build();
    }

    public static <T> Result success(T data) {
        return Result.success().code(GlobalConstants.RESULT_CODE_SUCCESS).data(data).build();
    }

    public static Result<Boolean> success(String message) {
        return Result.success().code(GlobalConstants.RESULT_CODE_SUCCESS).message(message).build();
    }
}
