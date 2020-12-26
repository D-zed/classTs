package util.aes;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author dzd
 */
public class AesUtil {

    // constants
    private static final String DEFAULT_CHARSET = "utf-8";
    private static final String KEY_ALGORITHM = "AES";
    private static final String ALGORITHM_STR = KEY_ALGORITHM + "/CBC/PKCS5Padding";
    private static final String IV = "1234567890123456";


    /**
     * AES 加密
     *
     * @param content
     * @param key
     * @param algorithmName     AES 加密方法
     * @param prngAlgorithmName SHA1PRNG
     * @param charSet           utf-8
     * @param keyLen            128
     * @return aes key的加密方法
     */
    public static byte[] encrypt(String content, String key, String algorithmName, String prngAlgorithmName, String charSet, int keyLen) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance(algorithmName);
            SecureRandom random = SecureRandom.getInstance(prngAlgorithmName);
            random.setSeed(key.getBytes());
            kgen.init(keyLen, random);
            Cipher cipher = Cipher.getInstance(algorithmName);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), algorithmName));
            return cipher.doFinal(content.getBytes(charSet));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param content
     * @param key
     * @param charset
     * @param IV  偏移量
     * @return
     * @throws Exception
     */
    public  byte[] encrypta(String content, String key, String charset,String IV) throws Exception {
        // secretKeySpec
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(charset), KEY_ALGORITHM);
        // ivParameterSpec
        IvParameterSpec ivParameterSpec = new IvParameterSpec(IV.getBytes(charset));
        // cipher
        Cipher cipher = Cipher.getInstance(ALGORITHM_STR);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] bytes = cipher.doFinal(content.getBytes(charset));
        return bytes;
    }







    /**
     * @param bytes
     * @param key
     * @param charset
     * @param IV  偏移量
     * @return
     * @throws Exception
     */
    public  String decrypta(byte[] bytes, String key, String charset,String IV) throws Exception {
        // secretKeySpec
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(charset), KEY_ALGORITHM);
        // ivParameterSpec
        IvParameterSpec ivParameterSpec = new IvParameterSpec(IV.getBytes(charset));
        // cipher
        Cipher cipher = Cipher.getInstance(ALGORITHM_STR);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] result = cipher.doFinal(bytes);
        return new String(result, charset);
    }




    /**
     * @param content
     * @param key
     * @param algorithmName       AES
     * @param prngAlgorithmName   SHA1PRNG
     * @param charSet             utf-8
     * @param keyLen              128
     * @return
     */
    public static String decryptAES(byte[] content, String key,String algorithmName,String prngAlgorithmName, String charSet,int keyLen) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithmName);
            SecureRandom secureRandom = SecureRandom.getInstance(prngAlgorithmName);
            secureRandom.setSeed(key.getBytes());
            keyGenerator.init(keyLen, secureRandom);
            SecretKey secretKey = keyGenerator.generateKey();
            Cipher cipher = Cipher.getInstance(algorithmName);
            cipher.init( Cipher.DECRYPT_MODE, secretKey);
            byte[] encrypted = cipher.doFinal(content);
            return new String(encrypted, charSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}