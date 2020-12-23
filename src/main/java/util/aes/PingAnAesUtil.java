package util.aes;

import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class PingAnAesUtil {

    // mode_key:Cipher
    private static final Map<String, Cipher> CIPHERMAP_MAP = new HashMap<String, Cipher>();

    // constants
    private static final String DEFAULT_CHARSET = "utf-8";
    private static final String PRNG_ALGORITHM_NAME = "SHA1PRNG";
    private static final String ALGORITHM_NAME = "AES";
    private static final String ALGORITHM_STR = ALGORITHM_NAME + "/ECB/PKCS5Padding";

    /**
     * get cipher
     *
     * @param mode
     * @param key
     * @param charset
     * @return
     * @throws Exception
     */
    private static Cipher getCipher(int mode, String key, String charset) throws Exception {
        Cipher cipher = CIPHERMAP_MAP.get(mode + "_" + key);
        if (cipher != null) {
            return cipher;
        }

        // secretKeySpec
        SecureRandom secureRandom = SecureRandom.getInstance(PRNG_ALGORITHM_NAME);
        secureRandom.setSeed(key.getBytes(charset));
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM_NAME);
        keyGenerator.init(128, secureRandom);
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, ALGORITHM_NAME);
        // cipher
        cipher = Cipher.getInstance(ALGORITHM_STR);
        cipher.init(mode, secretKeySpec);
        // return
        CIPHERMAP_MAP.put(mode + "_" + key, cipher);
        return cipher;
    }

    public static String encrypt(String a, String key, String charset) throws Exception {
        Cipher cipher = getCipher(Cipher.ENCRYPT_MODE, key, charset);
        byte[] resultByte = cipher.doFinal(a.getBytes(charset));
        return parseByte2HexStr(resultByte);
    }


    public static String encryptFuxing(String a, String key) throws Exception {
        Cipher cipher = getCipher(Cipher.ENCRYPT_MODE, key, DEFAULT_CHARSET);
        byte[] resultByte = cipher.doFinal(a.getBytes(DEFAULT_CHARSET));
        return base64Encode(resultByte);
    }

    private static String base64Encode(byte[] bytes) {
        return (new BASE64Encoder()).encode(bytes);
    }

    public static String encrypt(String a, String key) throws Exception {
        return encrypt(a, key, DEFAULT_CHARSET);
    }

    public static String decrypt(String a, String key, String charset) throws Exception {
        Cipher cipher = getCipher(Cipher.DECRYPT_MODE, key, charset);
        byte[] inputByte = parseHexStr2Byte(a);
        byte[] resultByte = cipher.doFinal(inputByte);
        return new String(resultByte, charset);
    }

    public static String decrypt(String a, String key) throws Exception {
        return decrypt(a, key, DEFAULT_CHARSET);
    }

    private static String parseByte2HexStr(byte buf[]) throws Exception {
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

    private static byte[] parseHexStr2Byte(String hexStr) {
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
