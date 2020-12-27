package util.aes;

import util.base64.Base64Util;

public class Test {


    static String fuxingaeskey = "FOSUN2016";

    static String pingAnPayAesKey="N84TSP9N3WYXERKA";
    static String pingAnAesKey="Oib1oFb5HRTjI1ue";

    static String SHA1PRNG="SHA1PRNG";

    static String taikangAes="D21Uq65MzDF28PaEx7zO0dSM2WuG03z0";

    // constants
    private static final String DEFAULT_CHARSET = "utf-8";
    private static final String KEY_ALGORITHM = "AES";
    private static final String CBC = KEY_ALGORITHM + "/CBC/PKCS5Padding";
    private static final String IV = "1234567890123456";

    public static void main(String[] args) throws Exception {
        String str="adfasfasfasfsdfsfafaasfa";

        System.out.println("===========英式无aes");

        System.out.println("===========复星联合aes加密");

        String s4 = FuXingPayUtil.aesEncrypt(str, fuxingaeskey);
        System.out.println(s4);

        String encrypt = FuxingAesUtil.encrypt(str, fuxingaeskey);
        System.out.println(encrypt);
        String decrypt3 = FuxingAesUtil.decrypt(encrypt, fuxingaeskey);
        System.out.println(decrypt3);
        /**
         * @param dateStr        内容
         * @param keyStr        key
         * @param SHA1PRNG      随机数算法
         * @param iv           偏移量
         * @param suanfa       加密算法
         * @param kenLen       key长度
         * @return
         * @throws Exception
         */
        byte[] encrypt3 = Aes.encrypt(str, fuxingaeskey, SHA1PRNG, null, null, 128);
        System.out.println(Base64Util.base64Encode(encrypt3));
        String decrypt4 = Aes.decrypt(encrypt3, fuxingaeskey, SHA1PRNG, null, null, 128);
        System.out.println(decrypt4);

//
        System.out.println("---------平安健康通用加密----------");
        String encrypt1 = PingAnAesUtil.encrypt(str, pingAnAesKey);
        System.out.println(encrypt1);
        String decrypt2 = PingAnAesUtil.decrypt(encrypt1, pingAnAesKey);
        System.out.println(decrypt2);

        byte[] pinganAes = Aes.encrypt(str, pingAnAesKey, SHA1PRNG, null, Aes.ECB, 128);
        String s3 = Aes.parseByte2HexStr(pinganAes);
        System.out.println(s3);
        String pinganAesjiemi = Aes.decrypt(pinganAes, pingAnAesKey, SHA1PRNG, null, Aes.ECB, 128);

        System.out.println(pinganAesjiemi);

//
        System.out.println("----------泰康的aes加密---------");
        String s = TaiKangApiUtil.encryptAES(str, taikangAes);
        System.out.println(s);
        String s2 = TaiKangApiUtil.decryptAES(s, taikangAes);
        System.out.println(s2);

        byte[] encrypt2 = Aes.encrypt(str, taikangAes, SHA1PRNG, null, null, 128);
        String s1 = Base64Util.base64Encode(encrypt2);
        System.out.println(s1);
        String decrypt = Aes.decrypt(encrypt2, taikangAes, SHA1PRNG, null, null, 128);
        System.out.println(decrypt);


        System.out.println("-========平安支付的aes加密===========");
        String xxxxx = PingAnPayAESUtil1.encrypt(str, pingAnPayAesKey);
        System.out.println(xxxxx);
        String sfsfs = PingAnPayAESUtil1.decrypt(xxxxx, pingAnPayAesKey);
        System.out.println(sfsfs);

        byte[] encrypt4 = Aes.encrypt(str, pingAnPayAesKey, null,"1234567890123456", CBC, null);
        System.out.println(Base64Util.base64Encode(encrypt4));

        String decrypt1 = Aes.decrypt(encrypt4, pingAnPayAesKey, null, "1234567890123456", CBC, null);
        System.out.println(decrypt1);
    }
}