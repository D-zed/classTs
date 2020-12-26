package util.aes;

import org.apache.commons.lang3.StringUtils;
import util.base64.Base64Util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * https://blog.csdn.net/zhizhengguan/article/details/89283906
 */
public class Aes {

    private static final String KEY_ALGORITHM = "AES";
    //算法默认用 AES
    private static final String ECB = "AES/ECB/PKCS5Padding";//ECB算法
    private static final String CBC = "AES/CBC/PKCS5Padding";//CBC算法
    static String SHA1PRNG="SHA1PRNG";
    private static final String DEFAULT_CHARSET = "utf-8";
    private static final String IV = "1234567890123456";

    private static SecretKeySpec initSecretKey(SecureRandom	secureRandom,int kenLen,String iv,String key)  {
        if (StringUtils.isNotBlank(iv)){
            return new SecretKeySpec( key.getBytes(), KEY_ALGORITHM);
        }
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;
        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        //初始化此密钥生成器，使其具有确定的密钥大小
        //AES 要求密钥长度为 128

        if (secureRandom!=null){
            kg.init( kenLen, secureRandom );
        }else {
            kg.init(kenLen);
        }

        //生成一个密钥
        return   new SecretKeySpec(kg.generateKey().getEncoded(), "AES");
    }

    private static Key toKey(byte[] key){
        //生成密钥
        return new SecretKeySpec(key, KEY_ALGORITHM);
    }


    public static byte[] encrypt(String dateStr,String keyStr,String SHA1PRNG,String iv,String suanfa,Integer kenLen) throws Exception{
        byte [] key=keyStr.getBytes();
        byte[] data=dateStr.getBytes();
        SecureRandom	secureRandom=null;
        if (SHA1PRNG!=null){
            secureRandom 	= SecureRandom.getInstance( SHA1PRNG );
            secureRandom.setSeed( key );
        }
        SecretKeySpec secretKeySpec=null;
        try {
            // secretKeySpe
            if (secureRandom==null){
                secretKeySpec = new SecretKeySpec(key, KEY_ALGORITHM);
            }else {
                secretKeySpec = initSecretKey(secureRandom,kenLen,iv,keyStr);
            }

        }catch (Exception e){
            System.out.println(e);
        }

        Cipher cipher;
        //实例化
        if (suanfa==null){
            cipher = Cipher.getInstance(KEY_ALGORITHM);
        }else {
            cipher = Cipher.getInstance(suanfa);
        }

        //使用密钥初始化，设置为加密模式
        if (iv!=null){
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV.getBytes(DEFAULT_CHARSET));
            cipher.init( Cipher.ENCRYPT_MODE, secretKeySpec   ,ivParameterSpec);
        }else {
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        }
        //执行操作
        return cipher.doFinal(data);
    }



    public static String decrypt(byte[] data,String keyStr,String SHA1PRNG,String iv,String suanfa,Integer kenLen) throws Exception{
        byte [] key=keyStr.getBytes();

        SecureRandom	secureRandom=null;
        if (SHA1PRNG!=null){
            secureRandom 	= SecureRandom.getInstance( SHA1PRNG );
            secureRandom.setSeed( key );
        }
        SecretKeySpec secretKeySpec=null;
        try {
            // secretKeySpe
            if (secureRandom==null){
                secretKeySpec = new SecretKeySpec(key, KEY_ALGORITHM);
            }else {
                secretKeySpec = initSecretKey(secureRandom,kenLen,iv,keyStr);
            }

        }catch (Exception e){
            System.out.println(e);
        }

        Cipher cipher;
        //实例化
        if (suanfa==null){
            cipher = Cipher.getInstance(KEY_ALGORITHM);
        }else {
            cipher = Cipher.getInstance(suanfa);
        }

        //使用密钥初始化，设置为加密模式
        if (iv!=null){
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV.getBytes(DEFAULT_CHARSET));
            cipher.init( Cipher.DECRYPT_MODE, secretKeySpec   ,ivParameterSpec);
        }else {
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        }
        //执行操作
        return new String( cipher.doFinal(data),DEFAULT_CHARSET);
    }


    public static String parseByte2HexStr(byte buf[]) throws Exception {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }



    static String fuxingaeskey = "FOSUN2016";

    static String pingAnAesKey="N84TSP9N3WYXERKA";

    public static void main(String[] args) throws Exception {
        String data="adfasfasfasfsdfsfafaasfa";
        byte[] encrypt = encrypt(data, fuxingaeskey,"SHA1PRNG", null,null,256);
//k4eVbzxfaMthpeRUFoKf/BUnOma0KSn8SMiFnGxDm1U=     不传SHA1PRNG
//Lh5HXRQduvKIpWNsPUYWnxJJzDC1n64MzB5wnKqxzCA=     传SHA1PRNG
//SdjJE3U0KiV9ilOJhNS+yPXMo5KqrNe5c5kO5JxN5LM=    CBC模
//XpsU2g+GaaC3aplX4AMGt7KYLXejNj0sWwnEzZDxx7E=    CBC加 shaiprng
        //貌似传了Sha1prng是  ecb模式
        String s1 = Base64Util.base64Encode(encrypt);
        System.out.println(s1);
    }
}