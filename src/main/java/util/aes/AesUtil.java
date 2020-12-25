package util.aes;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * @author dzd
 */
public class AesUtil {

    /**
     * AES 加密
     *
     * @param content
     * @param key
     * @param algorithmName     AES 加密方法
     * @param prngAlgorithmName SHA1PRNG
     * @param charSet           utf-8
     * @param cipherMode        ENCRYPT_MODE
     * @param keyLen            128
     * @return aes key的加密方法
     */
    public static byte[] encrypt(String content, String key, String algorithmName, String prngAlgorithmName, String charSet, int cipherMode, int keyLen) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance(algorithmName);
            SecureRandom random = SecureRandom.getInstance(prngAlgorithmName);
            random.setSeed(key.getBytes());
            kgen.init(keyLen, random);
            Cipher cipher = Cipher.getInstance(algorithmName);
            cipher.init(cipherMode, new SecretKeySpec(kgen.generateKey().getEncoded(), algorithmName));
            return cipher.doFinal(content.getBytes(charSet));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @param content
     * @param key
     * @param algorithmName       AES
     * @param prngAlgorithmName   SHA1PRNG
     * @param charSet             utf-8
     * @param cipherMode          Cipher.DECRYPT_MODE
     * @param keyLen              128
     * @return
     */
    public static String decryptAES(byte[] content, String key,String algorithmName,String prngAlgorithmName, String charSet, int cipherMode,int keyLen) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithmName);
            SecureRandom secureRandom = SecureRandom.getInstance(prngAlgorithmName);
            secureRandom.setSeed(key.getBytes());
            keyGenerator.init(keyLen, secureRandom);
            SecretKey secretKey = keyGenerator.generateKey();
            Cipher cipher = Cipher.getInstance(algorithmName);
            cipher.init( cipherMode, secretKey);
            byte[] encrypted = cipher.doFinal(content);
            return new String(encrypted, charSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}