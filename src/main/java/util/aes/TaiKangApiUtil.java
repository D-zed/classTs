package util.aes;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.MessageDigest;
import java.security.SecureRandom;

/**
 * 泰康api相关工具方法
 *
 * @author dzd
 */
public class TaiKangApiUtil {


    /**
     * AES 加密
     *
     * @param content
     * @param key
     * @return
     */
    public static String encryptAES(String content, String key) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            //需手动指定 SecureRandom 随机数生成规则，否则在Linux上可能生成随机key
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(key.getBytes());
            keyGenerator.init(128, secureRandom);
            SecretKey secretKey = keyGenerator.generateKey();
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encrypted = cipher.doFinal(content.getBytes("utf-8"));
            return Base64.encodeBase64String(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * AES 加密
     *
     * @param content
     * @param key
     * @return
     */
    public static String encryptAESFuxing(String content, String key) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            //需手动指定 SecureRandom 随机数生成规则，否则在Linux上可能生成随机key
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(key.getBytes());
            keyGenerator.init(128, secureRandom);
            SecretKey secretKey = keyGenerator.generateKey();
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encrypted = cipher.doFinal(content.getBytes("utf-8"));
            return Base64.encodeBase64String(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * AES解密
     *
     * @param content
     * @param key
     * @return
     */
    public static String decryptAES(String content, String key) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(key.getBytes());
            keyGenerator.init(128, secureRandom);
            SecretKey secretKey = keyGenerator.generateKey();
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] encrypted = cipher.doFinal(Base64.decodeBase64(content));
            return new String(encrypted, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * MD5运算
     *
     * @param content
     * @return
     */
    public static String md5(String content) {
        try {
            return Base64.encodeBase64String(MessageDigest.getInstance("MD5").digest(content.getBytes("utf-8")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}