package naixue.four.homework;

import contansts.StringPool;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 自定义网络类加载器
 *
 * @author dzd
 */
public class MyDefineClassLoader extends ClassLoader {

    String host;
    int port;

    String url;

    private MyDefineClassLoader(String host, int port) {
        this.host = host;
        this.port = port;

    }

    private MyDefineClassLoader(String url) {
        this.host = host;
        this.port = port;

    }

    @Override
    public Class findClass(String name) {
        byte[] b = loadClassData(name);
        return defineClass(name, b, 0, b.length);
    }

    private byte[] loadClassData(String name) {

        String path = getPath(name);
        InputStream inputStream;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            URL source = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) source.openConnection();
            inputStream = conn.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            int i = 0;
            while ((i = bufferedInputStream.read()) != -1) {
                outputStream.write(i);
            }
            return outputStream.toByteArray();
        } catch (IOException e) {

            throw new RuntimeException("url地址无法获取数据");
        }
    }

    private String getPath(String name) {
        String url = host + StringPool.COLON + port + StringPool.SLASH + name;
        return url;
    }
}
