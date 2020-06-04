package jsop.tst1;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JsoupUtil {

    //截取字段
    public static String sub="为了避免损失，大家一定要注意！";
    //结尾匹配
    public static String reg="</p>";


    @Test
    public void test1() throws IOException {

        //模板
        String htmlStr = loadHTML();
        System.out.println(htmlStr);
        Document doc = Jsoup.parse(htmlStr);
        doc.getElementsByClass("label2").empty().append("公众号左");
        doc.getElementsByClass("label3").empty().append("公众号右");
        doc.getElementsByClass("time").empty().append("n天前");
        doc.getElementsByClass("headerName").empty().append("新的标题");
        //文章内容
        String jsContent = loadHTMLContent();
        //文章内容初步处理
        String jsContentTarget = substringContent(jsContent);
        //获取截断的位置
        Integer index= getSubIndex(jsContentTarget,sub);
        //截断处前的内容
        jsContentTarget = jsContentTarget.substring(0, index);
        //截断处后边的内容
        String hiddenContent = jsContentTarget.substring(index + 1);
        //将处理好的文章内容塞回去
        doc.getElementById("js_content").html(jsContentTarget);
        //截断后的内容放在这里
        doc.getElementById("hidAera").html(hiddenContent);

        String s = doc.toString();
        System.out.println(s);

        //输出到对应的目录
        Files.write(Paths.get("H:\\jsoutest\\result.html"),s.getBytes());
    }



    private String loadHTML() {

        StringBuilder stringBuilder = new StringBuilder();

        try (
                //                                                                                          jvm的字符集
                Stream<String> stream = Files.lines(Paths.get("H:\\jsoutest\\advertisementMobile.html"),Charset.defaultCharset())) {
            List<String> collect = stream.collect(Collectors.toList());
            for (String s : collect) {
                //stringBuilder.append(s).append("\n");
                stringBuilder.append(s);
            }
        } catch (Exception e) {
            //Your exception handling here
        }
        return stringBuilder.toString();
    }

    private String loadJsContentSource() {

        StringBuilder stringBuilder = new StringBuilder();

        try (
                Stream<String> stream = Files.lines(Paths.get("H:\\jsoutest\\jscontentsource.txt"),Charset.defaultCharset())) {
            List<String> collect = stream.collect(Collectors.toList());
            for (String s : collect) {
                //stringBuilder.append(s).append("\n");
                stringBuilder.append(s);
            }
        } catch (Exception e) {
            //Your exception handling here
        }
        return stringBuilder.toString();
    }

    private String loadJsContentTarget() {

        StringBuilder stringBuilder = new StringBuilder();

        try (
                Stream<String> stream = Files.lines(Paths.get("H:\\jsoutest\\jscontenttarget.txt"),Charset.defaultCharset())) {
            List<String> collect = stream.collect(Collectors.toList());
            for (String s : collect) {
                //stringBuilder.append(s).append("\n");
                stringBuilder.append(s);
            }
        } catch (Exception e) {
            //Your exception handling here
        }
        return stringBuilder.toString();
    }


    @Test
    public void Test2() {
        String s = loadHTMLContent();
        int i = s.lastIndexOf("<");
        int i1 = s.indexOf(">");

        String substring = s.substring(i1 + 1, i);

        Document parse = Jsoup.parse(substring);

        System.out.println(substring);
        System.out.println(s);
        System.out.println(i);
        System.out.println(i1);
    }


    @Test
    public void test3(){
        String jscontent = loadJsContentSource();
        jscontent = substringContent(jscontent);
        Integer s = getSubIndex(jscontent, sub);
        System.out.println(s);

        String str="我饿死了妈的";
        String substring = str.substring(3, str.length());
        System.out.println(substring);
    }





    private String loadHTMLContent() {

        Connection connect = Jsoup.connect("https://mp.weixin.qq.com/s/h4vYB2mWtOlFQLdhA_hBDQ");

       /*可以转换编码
       Document response = Jsoup.parse(new String(
                connect.execute().bodyAsBytes(),"ISO-8859-15"));*/
        try {
            Document document = connect.get();
            Element js_content = document.getElementById("js_content");
            String s = js_content.toString();
            return s;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String substringContent(String source) {
        int i = source.lastIndexOf("<");
        int i1 = source.indexOf(">");
        return source.substring(i1 + 1, i).trim();
    }


    private Integer getSubIndex(String jsContentTarget, String sub) {
        int i = jsContentTarget.indexOf(sub);
        int index=0;
        char[] chars = jsContentTarget.toCharArray();
        for (int j = i+sub.length(); j < chars.length; j++) {
            String tmp = String.valueOf(chars[j]) + chars[j + 1] + chars[j + 2] + chars[j + 3];
            if (tmp.equals(reg)){
                index=j;
                break;
            }
        }
        return index+4;
    }
}
