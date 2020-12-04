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

        System.out.println();
    }

}