package zhouyang.fanxing;

import zhouyang.fastjson.JsonUtil;

public class TestFanxing {

    public static void main(String[] args) {
        Result<Boolean> fail = GlobalResultUtil.fail(ErrorCodesEnum.PARAMS_VALIAD_ERROR);
        System.out.println(JsonUtil.toJsonFormat(fail));
        System.out.println(JsonUtil.toJsonFormatSort(fail));
        System.out.println(JsonUtil.toJsonFormatMapSort(fail));
    }

}