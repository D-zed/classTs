package util.aes;

import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * aes加密 对应的是AesUtil
 *
 * @author dzd
 */
@Slf4j
public class FuxingAesUtil {
    /**
     * @Function: AesEncryptStrategyImpl.java
     * @Description: aes加密
     * @param: orignalStr 明文
     * @param: encryKey aes密钥
     * @return：返回结果描述
     * @throws：异常描述
     * @author: tit_xuemenglin
     * @date: 2019年3月23日 下午3:48:47
     * <p>
     * Modification History:
     * Date         Author          Version            Description
     * ---------------------------------------------------------*
     * 2019年3月23日     tit_xuemenglin           v1.0.0               修改原因
     */
    public static String encrypt(String orignalStr, String encryKey) {
        String encryptMsg = "";
        try {
            encryptMsg = base64Encode(aesEncryptToBytes(orignalStr, encryKey));
        } catch (Exception e) {
            log.error("AES加密失败", e);
        }
        return encryptMsg;
    }

    private static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(encryptKey.getBytes());
        kgen.init(128, random);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(1, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
        return cipher.doFinal(content.getBytes("utf-8"));
    }

    private static String base64Encode(byte[] bytes) {
        return (new BASE64Encoder()).encode(bytes);
    }

    /**
     * @Function: AesEncryptStrategyImpl.java
     * @Description: AES解密
     * @param:@param encryptStr 密文
     * @param:@param encryKey  aes密钥
     * @param:@return 描述：
     * @return：返回结果描述
     * @throws：异常描述
     * @author: tit_xuemenglin
     * @date: 2019年3月23日 下午3:56:59
     * <p>
     * Modification History:
     * Date         Author          Version            Description
     * ---------------------------------------------------------*
     * 2019年3月23日     tit_xuemenglin           v1.0.0               修改原因
     */
    public static String decrypt(String encryptStr, String encryKey) {
        String decrypMsg = "";
        try {
            decrypMsg = aesDecryptByBytes(base64Decode(encryptStr), encryKey);
        } catch (Exception e) {
            log.error("AES解密失败", e);
        }
        return decrypMsg;
    }

    private static byte[] base64Decode(String base64Code) throws Exception {
        if (!StringUtil.isNullOrEmpty(base64Code)) {
            return (new BASE64Decoder()).decodeBuffer(base64Code);
        }
        return null;
    }

    private static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(decryptKey.getBytes());
        kgen.init(128, random);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(2, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }
}
