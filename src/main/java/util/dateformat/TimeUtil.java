package util.dateformat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author dzd
 */
public class TimeUtil {

    public final static DateTimeFormatter FORMATTER1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public final static DateTimeFormatter FORMATTER2 = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    public final static DateTimeFormatter FORMATTER3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 投保日期
     * 2016-12-22
     *
     * @return String
     */
    public static String getCurrentTimeStr(DateTimeFormatter formatStr) {
        LocalDate localDate = LocalDate.now();
        return formatStr.format(localDate);
    }

    /**
     * 投保日期
     *
     * @return String
     */
    public static String getCurrentTimeStr() {
        LocalDateTime localDate = LocalDateTime.now();
        return FORMATTER3.format(localDate);
    }



    public static String dateFormat(String timeStr,DateTimeFormatter before,DateTimeFormatter after) throws ParseException {

        //将字符串解析成date
        LocalDate parse = LocalDate.parse(timeStr, before);

        return parse.format(after);
    }


}
