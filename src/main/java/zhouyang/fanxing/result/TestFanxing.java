package zhouyang.fanxing.result;

import zhouyang.fanxing.fanxingclass.ChildC;

/**
 * 泛型通配符
 * @author dzd
 */
public class TestFanxing {

    public static void main(String[] args) {

        Result<Boolean> aaa = GlobalResultUtil.success("aaa");

        ChildC childC = new ChildC();
        Result<ChildC> success = GlobalResultUtil.success(childC);

        Result<Object> fail = GlobalResultUtil.fail(ErrorCodesEnum.DATA_BIND_ERROR);

        Result<Object> aaa1 = Result.failure().message("aaa").build();

        Result<Object> a = Result.failure().code(GlobalConstants.RESULT_CODE_FAILURE).message("a").build();

        System.out.println();
    }

}