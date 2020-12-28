package util.aes;

import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
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
    public static final String ECB = "AES/ECB/PKCS5Padding";//ECB算法
    public static final String CBC = "AES/CBC/PKCS5Padding";//CBC算法
    static String SHA1PRNG = "SHA1PRNG";
    private static final String DEFAULT_CHARSET = "utf-8";
    private static final String IV = "1234567890123456";

    private static SecretKeySpec initSecretKey(SecureRandom secureRandom, int kenLen, String iv, String key) {
        if (StringUtils.isNotBlank(iv)) {
            return new SecretKeySpec(key.getBytes(), KEY_ALGORITHM);
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

        if (secureRandom != null) {
            kg.init(kenLen, secureRandom);
        } else {
            kg.init(kenLen);
        }

        //生成一个密钥
        return new SecretKeySpec(kg.generateKey().getEncoded(), KEY_ALGORITHM);
    }

    private static Key toKey(byte[] key) {
        //生成密钥
        return new SecretKeySpec(key, KEY_ALGORITHM);
    }


    /**
     * @param dateStr  内容
     * @param keyStr   key
     * @param randomAlgorithm 随机数算法
     * @param iv       偏移量
     * @param encryptionAlgorithm   加密算法 CEC ECB
     * @param kenLen   key长度
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(String dateStr, String keyStr, String randomAlgorithm, String iv, String encryptionAlgorithm, Integer kenLen) throws Exception {
        byte[] key = keyStr.getBytes();
        byte[] data = dateStr.getBytes();
        SecureRandom secureRandom = null;
        if (randomAlgorithm != null) {
            secureRandom = SecureRandom.getInstance(randomAlgorithm);
            secureRandom.setSeed(key);
        }
        SecretKeySpec secretKeySpec = null;
        try {
            // secretKeySpe
            if (secureRandom == null) {
                secretKeySpec = new SecretKeySpec(key, KEY_ALGORITHM);
            } else {
                secretKeySpec = initSecretKey(secureRandom, kenLen, iv, keyStr);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        Cipher cipher;
        //实例化
        if (encryptionAlgorithm == null) {
            cipher = Cipher.getInstance(KEY_ALGORITHM);
        } else {
            cipher = Cipher.getInstance(encryptionAlgorithm);
        }

        //使用密钥初始化，设置为加密模式
        if (iv != null) {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV.getBytes(DEFAULT_CHARSET));
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        } else {
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        }
        //执行操作
        return cipher.doFinal(data);
    }

    /**
     * @param data  内容
     * @param keyStr   key
     * @param randomAlgorithm 随机数算法
     * @param iv       偏移量
     * @param encryptionAlgorithm   加密算法 CEC ECB
     * @param kenLen   key长度
     * @return
     * @throws Exception
     */
    public static String decrypt(byte[] data, String keyStr, String randomAlgorithm, String iv, String encryptionAlgorithm, Integer kenLen) throws Exception {
        byte[] key = keyStr.getBytes();

        SecureRandom secureRandom = null;
        if (randomAlgorithm != null) {
            secureRandom = SecureRandom.getInstance(randomAlgorithm);
            secureRandom.setSeed(key);
        }
        SecretKeySpec secretKeySpec = null;
        try {
            // secretKeySpe
            if (secureRandom == null) {
                secretKeySpec = new SecretKeySpec(key, KEY_ALGORITHM);
            } else {
                secretKeySpec = initSecretKey(secureRandom, kenLen, iv, keyStr);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        Cipher cipher;
        //实例化
        if (encryptionAlgorithm == null) {
            cipher = Cipher.getInstance(KEY_ALGORITHM);
        } else {
            cipher = Cipher.getInstance(encryptionAlgorithm);
        }

        //使用密钥初始化，设置为加密模式
        if (iv != null) {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV.getBytes(DEFAULT_CHARSET));
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        } else {
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        }
        //执行操作
        return new String(cipher.doFinal(data), DEFAULT_CHARSET);
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

}
