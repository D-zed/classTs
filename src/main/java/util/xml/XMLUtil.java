package util.xml;


import com.sun.xml.bind.marshaller.CharacterEscapeHandler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * 封装了XML转换成object，object转换成XML的代码
 * https://blog.csdn.net/qustmeng/article/details/53706657?utm_medium=distribute.pc_relevant_ask_down.none-task-blog-baidujs-3.nonecase&depth_1-utm_source=distribute.pc_relevant_ask_down.none-task-blog-baidujs-3.nonecase
 *https://blog.csdn.net/by_yangge/article/details/100574586?utm_medium=distribute.pc_relevant.none-task-blog-title-3&spm=1001.2101.3001.4242
 * @author dzd
 */
public class XMLUtil {
    /**
     * 将对象直接转换成String类型的 XML输出
     *
     * @param obj
     *            obj
     * @return String
     */
    public static String convertToXml(Object obj) {
        // 创建输出流
        StringWriter sw = new StringWriter();
        try {
            // 利用jdk中自带的转换类实现
            JAXBContext context = JAXBContext.newInstance(obj.getClass());

            Marshaller marshaller = context.createMarshaller();
            // 格式化xml输出的格式
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            // 设置编码（默认编码就是utf-8）
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            // 是否省略xml头信息，默认不省略（false）
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);
            // 不进行转义字符的处理
            marshaller.setProperty(CharacterEscapeHandler.class.getName(), new CharacterEscapeHandler() {
                @Override
                public void escape(char[] ch, int start, int length, boolean isAttVal, Writer writer)
                    throws IOException {
                    writer.write(ch, start, length);
                }
            });
            // 将对象转换成输出流形式的xml
            marshaller.marshal(obj, sw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return sw.toString();
    }

    public static void main(String[] args) {
        FuXingBackXmlBean result = new FuXingBackXmlBean();
        FuXingBackXmlBeanHead fuXingBackXmlBeanHead = new FuXingBackXmlBeanHead();
        FuXingBackXmlBeanInfo fuXingBackXmlBeanInfo = new FuXingBackXmlBeanInfo();
        fuXingBackXmlBeanHead.setTransType("901");
        fuXingBackXmlBeanHead.setResultCode("健康告知回调成功");
        fuXingBackXmlBeanHead.setResultDesc("01");
        fuXingBackXmlBeanHead.setBusinessNo("MO20200928598981");
        fuXingBackXmlBeanHead.setSerialNo("MO20200928598981");
        fuXingBackXmlBeanHead.setSourceCode("WADBY04");
        result.setResHead(fuXingBackXmlBeanHead);

        FuXingApplyNewContBNFinfoParamDto fuXingApplyNewContBNFinfoParamDto=new FuXingApplyNewContBNFinfoParamDto();
        fuXingApplyNewContBNFinfoParamDto.setValStartDate("xxxxxxxxx");
        fuXingApplyNewContBNFinfoParamDto.setCardNo("ssssssssssss");
        List<FuXingApplyNewContBNFinfoParamDto> fuXingApplyNewContBNFinfoParamDtos=new ArrayList<>();
        fuXingApplyNewContBNFinfoParamDtos.add(fuXingApplyNewContBNFinfoParamDto);
        fuXingBackXmlBeanInfo.setShouyiren(fuXingApplyNewContBNFinfoParamDtos);

        fuXingBackXmlBeanInfo.setReqUrl(
            "https://admin.91duobaoyu.com/test/h5_new_media/sellShareAllNew/#/sellShareAllNew/fillForm?belongId=3293970825883648&pcId=602&refNumber=3293983131971584&uwMedicalId=JG000696992");
        result.setResultInfo(fuXingBackXmlBeanInfo);
        String healthCallBackResult = XMLUtil.convertToXml(result);

        System.out.println(healthCallBackResult);

    }

    /**
     * 将对象根据路径转换成xml文件
     *
     * @param obj
     *            obj
     * @param path
     *            path
     */
    public static void convertToXml(Object obj, String path) {
        try {
            // 利用jdk中自带的转换类实现
            JAXBContext context = JAXBContext.newInstance(obj.getClass());

            Marshaller marshaller = context.createMarshaller();
            // 格式化xml输出的格式
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            // 将对象转换成输出流形式的xml
            // 创建输出流
            FileWriter fw = null;
            try {
                fw = new FileWriter(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            marshaller.marshal(obj, fw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    /**
     * 将String类型的xml转换成对象
     */
    public static <T> T convertXmlStrToObject(Class<T> clazz, String xmlStr) {
        T xmlObject = null;
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            // 进行将Xml转成对象的核心接口
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader sr = new StringReader(xmlStr);
            xmlObject = (T)unmarshaller.unmarshal(sr);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xmlObject;
    }

    @SuppressWarnings("unchecked")
    /**
     * 将file类型的xml转换成对象
     */
    public static Object convertXmlFileToObject(Class clazz, String xmlPath) {
        Object xmlObject = null;
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            FileReader fr = null;
            try {
                fr = new FileReader(xmlPath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            xmlObject = unmarshaller.unmarshal(fr);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xmlObject;
    }
}