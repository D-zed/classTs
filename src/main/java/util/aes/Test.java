package util.aes;

import util.base64.Base64Util;

import javax.crypto.Cipher;

public class Test {


    static String fuxingmd5key = "ewealth";
    static String fuxingaeskey = "FOSUN2016";

    public static void main(String[] args) throws Exception {
        String str="adfasfasfasfsdfsfafaasfa";
        String encrypt = FuxingAesUtil.encrypt(str, fuxingaeskey);
        System.out.println(encrypt);

        String encrypt1 = PingAnAesUtil.encryptFuxing(str, fuxingaeskey);
        System.out.println(encrypt1);

        String s = TaiKangApiUtil.encryptAESFuxing(str, fuxingaeskey);
        System.out.println(s);

        byte[] encrypt2 = AesUtil.encrypt(str, fuxingaeskey, "AES", "SHA1PRNG", "utf-8", Cipher.ENCRYPT_MODE,128);
        String s1 = Base64Util.base64Encode(encrypt2);
        System.out.println(s1);

    }
}
