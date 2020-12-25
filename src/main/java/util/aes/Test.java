package util.aes;

import util.base64.Base64Util;

import javax.crypto.Cipher;

public class Test {


    static String fuxingmd5key = "ewealth";
    static String fuxingaeskey = "FOSUN2016";

    static String pingAnAesKey="N84TSP9N3WYXERKA";

    public static void main(String[] args) throws Exception {
        String str="adfasfasfasfsdfsfafaasfa";
        String encrypt = FuxingAesUtil.encrypt(str, pingAnAesKey);
        System.out.println(encrypt);

        String encrypt1 = PingAnAesUtil.encryptFuxing(str, pingAnAesKey);
        System.out.println(encrypt1);

        String s = TaiKangApiUtil.encryptAESFuxing(str, pingAnAesKey);
        System.out.println(s);

        byte[] encrypt2 = AesUtil.encrypt(str, pingAnAesKey, "AES", "SHA1PRNG", "utf-8", Cipher.ENCRYPT_MODE,256);
        String s1 = Base64Util.base64Encode(encrypt2);
        System.out.println(s1);
        String s2 = AesUtil.decryptAES(encrypt2, pingAnAesKey, "AES", "SHA1PRNG", "utf-8", Cipher.DECRYPT_MODE, 256);
        System.out.println(s2);

        String s3 = TaiKangApiUtil.decryptAES(s1, pingAnAesKey);
        System.out.println(s3);

        System.out.println("-===================");
        String encrypt3 = AESUtil1.encrypt(str, pingAnAesKey);
        System.out.println(encrypt3);
        String decrypt = AESUtil1.decrypt(encrypt3, pingAnAesKey);
        System.out.println(decrypt);

    }
}
