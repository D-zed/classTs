package util.aes;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
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
            KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithmName);
            //需手动指定 SecureRandom 随机数生成规则，否则在Linux上可能生成随机key
            SecureRandom secureRandom = SecureRandom.getInstance(prngAlgorithmName);
            secureRandom.setSeed(key.getBytes());
            keyGenerator.init(keyLen, secureRandom);
            SecretKey secretKey = keyGenerator.generateKey();
            Cipher cipher = Cipher.getInstance(algorithmName);
            cipher.init(cipherMode, secretKey);
            return cipher.doFinal(content.getBytes(charSet));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
