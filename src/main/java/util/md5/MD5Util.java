package util.md5;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 类的说明：MD5加密工具类
 **/
@Slf4j
public class MD5Util {

  static String  UTF8="utf-8";

    /**
     *
     * @param convertStr  convertStr
     * @param suanfa   MD5,SHA-1,SHA-256
     * @return
     */
    public static byte[] Md5(String convertStr,String suanfa) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance(suanfa);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("MD5 algorithm not available.  Fatal (should be in the JDK).");
        }


        return digest.digest(convertStr.getBytes(StandardCharsets.UTF_8));
    }


    public static String convertToStr(byte[] bytes){
        return String.format("%032x", new BigInteger(1, bytes));
    }

    /**
     * 平安pay的加密内容如下处理
     * @param bts bts
     * @return
     */
    public static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }


    public static void main(String[] args) {
    }
}
