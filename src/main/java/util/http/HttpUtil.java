package util.http;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.FormBody.Builder;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
/**
 * @author dzd
 */
@Slf4j
public class HttpUtil {

    //private static Logger logger = LoggerFactory.getLogger(com.duobaoyu.insure.tools.HttpUtil.class);

    // 设置各种超时时间;
    private static final OkHttpClient CLIENT = new OkHttpClient().newBuilder().readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).build();


    public static final String TAG = "MainActivity";
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * 发送http请求
     *
     * @param url  url
     * @param json json
     * @return String
     * @throws IOException IOException
     */
    public static String postJson(String url, String json) throws IOException {
        RequestBody requestBody = RequestBody.create(json, JSON);
        Request request = requestBuilder(url).post(requestBody).build();
        Response response = CLIENT.newCall(request).execute();
        if (!response.isSuccessful()) {
            log.error("url :{} , 请求失败 : {}", url, response);
            throw new IOException("Unexpected code " + response);
        }

        return response.body().string();
    }

    /**
     * 发送http请求 泰康的奇葩 500的时候也代表成功
     *
     * @param url  url
     * @param json json
     * @return String
     * @throws IOException IOException
     */
    public static String postJsonTaiKang(String url, String json) throws IOException {
        RequestBody requestBody = RequestBody.create(json, JSON);
        Request request = requestBuilder(url).post(requestBody).build();
        Response response = CLIENT.newCall(request).execute();
        if (response.body()==null) {
            log.error("url :{} , 请求失败 : {}", url, response);
            throw new IOException("Unexpected code " + response);
        }

        return response.body().string();
    }

    /**
     * 发送Multpart请求
     *
     * @param url url
     * @return String
     * @throws IOException IOException
     */
    public static String postMultpart(String url, Map<String, String> paramsMap, Map<String, File> fileMap) throws IOException {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        if (paramsMap != null) {
            for (Entry<String, String> entry : paramsMap.entrySet()) {
                builder.addFormDataPart(entry.getKey(), entry.getValue());
            }
        }
        if (fileMap != null) {
            String type = "multipart/form-data";
            for (Entry<String, File> entry : fileMap.entrySet()) {
                File file = entry.getValue();
                RequestBody fileBody = RequestBody.create(MediaType.parse(type), file);
                builder.setType(MultipartBody.FORM).addFormDataPart(entry.getKey(), file.getName(), fileBody);
            }
        }
        RequestBody requestBody = builder.build();
        Request request = new Request.Builder().url(url).post(requestBody).build();
        Response response = CLIENT.newCall(request).execute();
        return response.body().string();
    }


    public static String post(String url, Map<String, String> params) {

        Request.Builder builder = requestBuilder(url);
        return postForm(params, builder);
    }

    public static String post(String url, Map<String, String> params, Map<String, String> headers) {

        Request.Builder builder = requestBuilder(url);
        headers.forEach(builder::addHeader);
        return postForm(params, builder);
    }

    private static void downLoadFile(String url, OutputStream outputStream) {

        try {
            Request request = requestBuilder(url).get().build();
            Response response = CLIENT.newCall(request).execute();
            IOUtils.copy(response.body().byteStream(), outputStream);
        } catch (Exception e) {
            log.error("文件下载失败 : url : {}, {}", url, e);
            //throw new ServiceException("文件下载失败 ");
        }
    }

    private static String postForm(Map<String, String> params, Request.Builder builder) {

        Request request = builder.post(formBody(params)).build();
        Response response;
        try {
            response = CLIENT.newCall(request).execute();
            if (!response.isSuccessful()) {
                log.error("request fail !! {}", response);
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        } catch (IOException e) {
            log.error("request fail !!", e);
            //throw new ServiceException("请求失败");
        }

        return null;
    }



    /**
     * 请求body
     *
     * @param params params
     * @return RequestBody
     */
    private static RequestBody formBody(Map<String, String> params) {

        Builder buidler = new FormBody.Builder();
        SortedMap<String, String> sortParams = new TreeMap<>(params);
        Set<Entry<String, String>> set = sortParams.entrySet();
        for (Entry<String, String> entry : set) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (StringUtils.isNotBlank(value)) {
                buidler.add(key, value);
            }
        }
        return buidler.build();
    }

    private static Request.Builder requestBuilder(String url) {

        return new Request.Builder().url(url);
    }



    public  static String okHttpGet(String url) throws IOException {
        //不配url方法会报错，肯定要有访问地址的嘛
        //.Builder是Request内部类 .url()返回Builder .build()返回Request对象
        Request request = requestBuilder(url).build();
        Call call = CLIENT.newCall(request);

            Response response = call.execute();
            if (!response.isSuccessful()) {
                log.error("url :{} , 请求失败 : {}", url, response);
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
    }

    public static InputStream readImg(String imgUrl) {
        InputStream inputStream;
        try {
            URL imageUrl = new URL(imgUrl);
            HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
            inputStream = conn.getInputStream();
        } catch (IOException e) {
            log.error("excelUrl:" + imgUrl);
            throw new RuntimeException("url地址无法获取数据");
        }
        return inputStream;
    }


    public static void main(String[] args) throws Exception {

        File zipFile = new File("d:" + File.separator + "sssss.zip");
        String url = "http://192.168.1.213/home/201804/zip/201804101133.zip";
        downLoadFile(url, new FileOutputStream(zipFile));
    }
}