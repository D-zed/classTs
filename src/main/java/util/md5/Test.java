package util.md5;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class Test {


    static String fuxingmd5key = "ewealth";

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String str="adfasfasfasfsdfsfafaasfa";

        String s2 = MD5Util.Md5(str + fuxingmd5key);
        System.out.println(s2);

    }
}
