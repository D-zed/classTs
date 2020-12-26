package util.md5;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PinganPayUtil {
    public static final String CHANNEL_ID = "收银台渠道代码";  // 需要替换
    public static final String CHANNEL_KEY = "收银台渠道密钥"; // 需要替换

    public static final String DOMAIN = "http://test-mobile.health.pingan.com/ehis-hm/cashier/pay.do?"; // 测试地址

    public static void main(String[] args) throws UnsupportedEncodingException {


        /** 注意：下面的这些参数请根据实际情况参考收银台接口文档替换 **/

        String goodsDesc = "中文";
        String returnUrl = "页面跳转地址";
        String notifyUrl = "异步回调地址，目前只支持https";

        Map<String, String> encryMap = new HashMap<String, String>();
        encryMap.put("channel_order_no", "TEST000000000027675");
        encryMap.put("goods_desc", goodsDesc);
        encryMap.put("total_fee", "41400");
        encryMap.put("channel_id", CHANNEL_ID);
        encryMap.put("return_url", returnUrl);
        encryMap.put("notify_url", notifyUrl);
        String sign = encryParamMap(encryMap,CHANNEL_KEY);

        encryMap.put("sign_type", "SHA-256");
        encryMap.put("sign", sign);

        // 对 goods_desc、return_url、notify_url这些参数做urlencode
        encryMap.put("goods_desc", URLEncoder.encode(goodsDesc, "utf-8"));
        encryMap.put("return_url", URLEncoder.encode(returnUrl, "utf-8"));
        encryMap.put("notify_url", URLEncoder.encode(notifyUrl, "utf-8"));

    }

    public static String encryParamMap(Map<String, String> paramMap, String channelKey) {
        String encryStr = createLinkString(paramMap);
        encryStr += channelKey;
        try {
            return encryptPwd(encryStr, null, "utf-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     *
     * @param params
     *            需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);

            if (i == keys.size() - 1) {// 拼接时，不包括最后一丄1�7&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }

    /**
     * 对字符串加密,加密算法使用MD5,SHA-1,SHA-256,默认使用SHA-256
     *
     * @param strSrc
     *            要加密的字符串
     * @param encName
     *            加密类型
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encryptPwd(String strSrc, String encName, String charset) throws UnsupportedEncodingException {
        MessageDigest md = null;
        String strDes = null;

        byte[] bt = strSrc.getBytes(charset);
        try {
            if (encName == null || encName.equals("")) {
                encName = "SHA-256";
            }
            md = MessageDigest.getInstance(encName);
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }

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

}
