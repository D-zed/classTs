package util.stream;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

@Slf4j
public class StreamUtil {

    /**
     * 将一个字符串转化为输入流
     *
     * @param sInputString sInputString
     * @return InputStream
     */
    public static InputStream getStrToStream(String sInputString) {
        if (sInputString != null && !sInputString.trim().equals("")) {
            try {
                ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(sInputString.getBytes());
                return tInputStringStream;
            } catch (Exception ex) {
                log.error("StreamUtil getStrToStream error",ex);
            }
        }
        return null;
    }

    /**
     * 将一个输入流转化为字符串
     *
     * @param tInputStream tInputStream
     * @return String
     */
    public static String getStreamToStr(String tInputStream) {
        if (tInputStream != null) {
            try {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(tInputStream.getBytes());
                BufferedReader tBufferedReader = new BufferedReader(new InputStreamReader(byteArrayInputStream));
                StringBuffer tStringBuffer = new StringBuffer();
                String sTempOneLine;
                while ((sTempOneLine = tBufferedReader.readLine()) != null) {
                    tStringBuffer.append(sTempOneLine);
                }
                return tStringBuffer.toString();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
}
