package util.md5;

import util.aes.FuXingPayUtil;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class Test {


    static String fuxingmd5key = "ewealth";

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        String str = "adfasfasfasfsdfsfafaasfa";
        System.out.println("平安支付md5加密使用的是 SHA-256 算法");
        System.out.println("平安智能核保md5加密使用的是 SHA-256 算法");
        String s = PinganPayUtil.encryptPwd(str + fuxingmd5key, "SHA-256", MD5Util.UTF8);
        System.out.println(s);

        byte[] bytes = MD5Util.Md5(str + fuxingmd5key, "SHA-256");
        String s1 = MD5Util.bytes2Hex(bytes);
        System.out.println(s1);
        System.out.println("复星联合的加密算法是MD5  content+key方式加密");
        String s2 = FuXingPayUtil.md5(str + fuxingmd5key);
        System.out.println(s2);
        byte[] xxx = MD5Util.Md5(str + fuxingmd5key, "MD5");
        String s12 = MD5Util.bytes2Hex(xxx);
        System.out.println(s12);

        System.out.println("泰康 也是 MD5  content+key的方式");

        System.out.println("英式的MD5算法  key+content的方式加密");


    }
}