package zhouyang.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 讲解使用fastJson时候的一些参数
 * https://blog.csdn.net/zjkyx888/article/details/78673898?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-1.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-1.control
 *
 * QuoteFieldNames  输出key时是否使用双引号,默认为true
 * UseSingleQuotes  使用单引号而不是双引号,默认为false
 * WriteMapNullValue  是否输出值为null的字段,默认为false
 * UseISO8601DateFormat Date使用ISO8601格式输出，默认为false
 * WriteNullListAsEmpty  List字段如果为null,输出为[],而非null
 * WriteNullStringAsEmpty  字符类型字段如果为null,输出为”“,而非null
 * WriteNullNumberAsZero   数值字段如果为null,输出为0,而非null
 * WriteNullBooleanAsFalse  Boolean字段如果为null,输出为false,而非null
 * SortField                按字段名称排序后输出。默认为false
 * PrettyFormat             结果是否格式化,默认为false
 * WriteClassName           序列化时写入类型信息，默认为false。反序列化是需用到
 * WriteDateUseDateFormat   全局修改日期格式,默认为false。JSON.DEFFAULT_DATE_FORMAT = “yyyy-MM-dd”;JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat);
 * DisableCircularReferenceDetect  消除对同一对象循环引用的问题，默认为false
 * WriteSlashAsSpecial 对斜杠'/'进行转义
 * BrowserCompatible  将中文都会序列化为\\uXXXX格式，字节数会多一些，但是能兼容IE 6，默认为false
 * DisableCheckSpecialChar  一个对象的字符串属性中如果有特殊字符如双引号，将会在转成json时带有反斜杠转移符。如果不需要转义，可以使用这个属性。默认为false
 *
 * json注解
 * https://blog.csdn.net/weixin_36910300/article/details/79183471
 *
 * jsonType注解
 * https://blog.csdn.net/qingfengmuzhu1993/article/details/73498347
 *
 * 上面的参数对localDateTime的格式不生效 writeDateUseDateFormat
 * https://www.gitmemory.com/issue/alibaba/fastjson/2328/474177599
 * @author dzd
 */
public class JsonUtil {

    public static String toJsonFormat(Object obj) {
        //json自带的格式化参数
        SerializerFeature prettyFormat = SerializerFeature.PrettyFormat;
        return JSON.toJSONString(obj, prettyFormat);
    }

    public static String toJsonFormatSort(Object obj) {
        //json自带的格式化参数
        SerializerFeature prettyFormat = SerializerFeature.PrettyFormat;
        SerializerFeature sortField = SerializerFeature.SortField;
        return JSON.toJSONString(obj, prettyFormat,sortField);
    }

    public static String toJsonFormatMapSort(Object obj) {
        //json自带的格式化参数
        SerializerFeature prettyFormat = SerializerFeature.PrettyFormat;
        SerializerFeature sortField = SerializerFeature.MapSortField;
        return JSON.toJSONString(obj, prettyFormat,sortField);
    }

    public static String toJsonFormatMapSortAndNullVal(Object obj) {
        //json自带的格式化参数
        SerializerFeature prettyFormat = SerializerFeature.PrettyFormat;
        SerializerFeature sortField = SerializerFeature.MapSortField;
        SerializerFeature writeMapNullValue = SerializerFeature.WriteMapNullValue;
        return JSON.toJSONString(obj, prettyFormat,sortField,writeMapNullValue);
    }

    public static String toJsonFormatNotNullVal(Object obj) {
        //json自带的格式化参数
        SerializerFeature prettyFormat = SerializerFeature.PrettyFormat;
        SerializerFeature writeNullBooleanAsFalse = SerializerFeature.WriteNullBooleanAsFalse;
        SerializerFeature writeNullListAsEmpty = SerializerFeature.WriteNullListAsEmpty;
        SerializerFeature writeBigDecimalAsPlain = SerializerFeature.WriteBigDecimalAsPlain;
      //  SerializerFeature writeClassName = SerializerFeature.WriteClassName;
        SerializerFeature writeNonStringKeyAsString = SerializerFeature.WriteNonStringKeyAsString;
        SerializerFeature writeNonStringValueAsString = SerializerFeature.WriteNonStringValueAsString;
        SerializerFeature writeNullNumberAsZero = SerializerFeature.WriteNullNumberAsZero;
        SerializerFeature writeNullStringAsEmpty = SerializerFeature.WriteNullStringAsEmpty;
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
        SerializerFeature writeDateUseDateFormat = SerializerFeature.WriteDateUseDateFormat;

        return JSON.toJSONString(obj,
                writeNullBooleanAsFalse,
                writeNullListAsEmpty,
                writeBigDecimalAsPlain,
              //  writeClassName,
                writeNonStringKeyAsString,
                writeNonStringValueAsString,
                writeNullNumberAsZero,
                writeNullStringAsEmpty,
                //格式化时间 经过测试发现只格式化Date类型无法格式化LocalDate
                writeDateUseDateFormat,
                prettyFormat
                );
    }


    /**
     * 此方法可以全局格式化 各种时间
     * @param obj
     * @return
     */
    public static String toJsonFormatNotNullValNew(Object obj) {
        //json自带的格式化参数
        SerializerFeature prettyFormat = SerializerFeature.PrettyFormat;
        SerializerFeature writeNullBooleanAsFalse = SerializerFeature.WriteNullBooleanAsFalse;
        SerializerFeature writeNullListAsEmpty = SerializerFeature.WriteNullListAsEmpty;
        SerializerFeature writeBigDecimalAsPlain = SerializerFeature.WriteBigDecimalAsPlain;
        //  SerializerFeature writeClassName = SerializerFeature.WriteClassName;
        SerializerFeature writeNonStringKeyAsString = SerializerFeature.WriteNonStringKeyAsString;
        SerializerFeature writeNonStringValueAsString = SerializerFeature.WriteNonStringValueAsString;
        SerializerFeature writeNullNumberAsZero = SerializerFeature.WriteNullNumberAsZero;
        SerializerFeature writeNullStringAsEmpty = SerializerFeature.WriteNullStringAsEmpty;
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
        SerializerFeature writeDateUseDateFormat = SerializerFeature.WriteDateUseDateFormat;

        return JSON.toJSONStringWithDateFormat(obj,JSON.DEFFAULT_DATE_FORMAT,
                writeNullBooleanAsFalse,
                writeNullListAsEmpty,
                writeBigDecimalAsPlain,
                //  writeClassName,
                writeNonStringKeyAsString,
                writeNonStringValueAsString,
                writeNullNumberAsZero,
                writeNullStringAsEmpty,
                //格式化时间 经过测试发现只格式化Date类型无法格式化LocalDate
                writeDateUseDateFormat,
                prettyFormat
        );
    }



    public static void main(String[] args) {
        TaiKangApplicantParamDTO taiKangApplicantParamDTO=new TaiKangApplicantParamDTO();
        taiKangApplicantParamDTO.setLocalDateTime(LocalDateTime.now());
        taiKangApplicantParamDTO.setAge(23);
        taiKangApplicantParamDTO.setName("小明");
        taiKangApplicantParamDTO.setSex("1");
        taiKangApplicantParamDTO.setAddress("地球");
        taiKangApplicantParamDTO.setPhone("13143556789");
        taiKangApplicantParamDTO.setType("1");
        taiKangApplicantParamDTO.setCurrentDate(new Date());

        System.out.println(JsonUtil.toJsonFormat(taiKangApplicantParamDTO));
        System.out.println(JsonUtil.toJsonFormatSort(taiKangApplicantParamDTO));
        System.out.println(JsonUtil.toJsonFormatMapSort(taiKangApplicantParamDTO));
        System.out.println(JsonUtil.toJsonFormatMapSortAndNullVal(taiKangApplicantParamDTO));
        System.out.println(JsonUtil.toJsonFormatNotNullVal(taiKangApplicantParamDTO));
        System.out.println(JsonUtil.toJsonFormatNotNullValNew(taiKangApplicantParamDTO));
    }

}
