package util.base64;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

@Slf4j
public class Base64Util {

    /**
     * 网络图片转换Base64的方法
     *
     * @param imgUrlStr imgUrlStr
     */
    public static String onlineImage2Base64(String imgUrlStr) {
        ByteArrayOutputStream data = new ByteArrayOutputStream();
        InputStream is = null;
        try {
            // 创建URL
            URL url = new URL(imgUrlStr);
            byte[] by = new byte[1024];
            // 创建链接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);

            is = conn.getInputStream();
            // 将内容读取内存中
            int len = -1;
            while ((len = is.read(by)) != -1) {
                data.write(by, 0, len);
            }
            // 对字节数组Base64编码
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(data.toByteArray());
        } catch (Exception e) {
            log.error("onlineImage2Base64 error ", e);
        } finally {
            System.out.println("网络图片转换Base64结束");
            if (is != null) {
                // 关闭流
                try {
                    is.close();
                } catch (Exception e) {
                    log.error("onlineImage2Base64 close error ", e);
                }
            }
        }
        return null;
    }


    /**
     * 本地图片转换Base64的方法
     *
     * @param imgPath imgPath
     */
    public static String localImage2Base64(String imgPath) {
        InputStream in = null;
        // 读取图片字节数组
        try {
            in = new FileInputStream(imgPath);
            byte[] data = new byte[in.available()];
            in.read(data);
            // 对字节数组Base64编码
            BASE64Encoder encoder = new BASE64Encoder();
            // 返回Base64编码过的字节数组字符串
            return encoder.encode(Objects.requireNonNull(data));
        } catch (Exception e) {
            log.error("localImage2Base64 error ", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    log.error("localImage2Base64 close error ", e);

                }
            }
        }
        return null;
    }

    public static InputStream base64ToIo(String strBase64) {
        String string = strBase64;
        try {
            // 解码，然后将字节转换为文件   //将字符串转换为byte数组
            byte[] bytes = new BASE64Decoder().decodeBuffer(string);
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            return in;

        } catch (Exception e) {
            log.error("base64ToIo error ", e);
        }
        return null;
    }
}
