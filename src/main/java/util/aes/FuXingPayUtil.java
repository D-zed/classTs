package util.aes;

import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


public class FuXingPayUtil {
    String aesKey = "_SINOPAY_WECHAT";
    String md5Key = "abc123";
    /**
     * 分隔符
     */
    private static final String signal = "|";


    /**
     * @param @param  obj
     * @param @return
     * @return Map<String, String>
     * @throws
     * @Title: getValueMap
     * @Description: 按照参数名根据ASCII码升序, 参数为空，则不组装
     */
    public Map<String, String> getValueMap(Object obj) {

        Map<String, String> map = new HashMap<String, String>();
        // System.out.println(obj.getClass());
        // 获取f对象对应类中的所有属性域
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0, len = fields.length; i < len; i++) {
            String varName = fields[i].getName();
            try {
                // 获取原来的访问控制权限
                boolean accessFlag = fields[i].isAccessible();
                // 修改访问控制权限
                fields[i].setAccessible(true);
                // 获取在对象f中属性fields[i]对应的对象中的变量
                Object o = fields[i].get(obj);
                if (o != null && !"".equals(o)) {
                    map.put(varName, o.toString());
                }

                // System.out.println("传入的对象中包含一个如下的变量：" + varName + " = " + o);
                // 恢复访问控制权限
                fields[i].setAccessible(accessFlag);
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
        return map;

    }

    /**
     * 加签
     *
     * @param encryMsgs
     * @param signKey
     * @return
     * @throws Exception
     */
    public static String encryMD5(Map<String, String> encryMsgs, String signKey) throws Exception {
        //校验信息完整性
        if (signKey == null || "".equals(signKey.trim()) || encryMsgs == null || encryMsgs.size() < 1) {
            System.out.println("签名信息不完整！");
        }

        //首先对非空数据进行排序
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        for (String tKey : encryMsgs.keySet()) {
            String tValue = encryMsgs.get(tKey);
            if (tValue != null && !"".equals(tValue)) {
                treeMap.put(tKey, encryMsgs.get(tKey));
            }
        }
        treeMap.put("key", signKey);
        //拼接字符串
        StringBuffer sb = new StringBuffer();
        for (String tKey : treeMap.keySet()) {
            String value = treeMap.get(tKey);
            if ("payAmount".equals(tKey)) {
                value = transAmount(new BigDecimal(value));
            }
            sb.append(tKey);
            sb.append("=");
            sb.append(value);
            sb.append(signal);
        }
        String tToEncryStr = sb.toString().substring(0, sb.length() - 1);
        //签名
        String md5Check = md5(tToEncryStr + signKey);
        return md5Check;
    }

    /**
     * 对原始数据进行MD5加密，编码使用UTF-8，MD5加密不可逆。
     *
     * @param cPlainText 需要加密的原始数据
     * @return MD5加密后的字符串
     */
    public static String md5(String cPlainText) {
        StringBuffer tBuf = new StringBuffer();
        try {
            MessageDigest tMd = MessageDigest.getInstance("MD5");
            tMd.update(cPlainText.getBytes("utf-8"));
            byte[] tByte = tMd.digest();
            int i;
            for (int j = 0; j < tByte.length; j++) {
                i = tByte[j];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    tBuf.append("0");
                }
                tBuf.append(Integer.toHexString(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tBuf.toString();
    }


    /**
     * AES加密为base 64 code
     *
     * @param content    待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的base 64 code
     * @throws Exception
     */
    public static String aesEncrypt(String content, String encryptKey) throws Exception {
        return base64Encode(aesEncryptToBytes(content, encryptKey));
    }

    /**
     * base 64 encode
     *
     * @param bytes 待编码的byte[]
     * @return 编码后的base 64 code
     */
    public static String base64Encode(byte[] bytes) {
        return new BASE64Encoder().encode(bytes);
    }


    /**
     * AES加密
     *
     * @param content    待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的byte[]
     * @throws Exception
     */
    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(encryptKey.getBytes());
        kgen.init(128, random);

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));

        return cipher.doFinal(content.getBytes("utf-8"));
    }


    /**
     * 金额  （分）
     *
     * @param tPayAmount
     * @return
     */
    private static String transAmount(BigDecimal tPayAmount) {
        BigDecimal fen = tPayAmount.multiply(new BigDecimal("100"));
        long fenAmount = fen.intValue();
        String money = String.valueOf(fenAmount);
        return money;
    }


}
