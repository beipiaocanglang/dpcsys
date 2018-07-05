package dpcsys.consumption.frame.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author : sunpanhu
 * create time : 2018-5-25 下午4:58
 */
public class DateConvertUtils {

    /**
     * String 转 Date
     * @param stringDate 要转换的时间
     * @param dataFormat 要转换的事时间格式
     * @return
     */
    public static Date stringToDate(String stringDate, String dataFormat) {
        SimpleDateFormat format = new SimpleDateFormat(dataFormat);
        try {
            Date date = format.parse(stringDate);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Date 转 String
     * @param date 要转换的时间
     * @param dataFormat 要转换的事时间格式
     * @return
     */
    public static String dateToString(Date date, String dataFormat) {
        SimpleDateFormat format = new SimpleDateFormat(dataFormat);

        try {
            String stringDate = format.format(date);

            return stringDate;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 时间相减，返回指定格式的字符串
     * author : sunpanhu
     * createTime : 2018-5-29 上午10:43
     * @return
     */
    public static long dateDiff (long diffTime){
        //一天的时间毫秒值
        long daysMillisecond = 1000 * 60 * 60 * 24;
        //一小时的时间毫秒值
        long hoursMillisecond = 1000 * 60 * 60;
        //一分钟的时间毫秒值
        long minutesMillisecond = 1000 * 60;
        //一秒的时间毫秒值
        //long millisecond = 1000;

        //相差天数 = 总的毫秒值 / 一天的时间毫秒值
        long days = diffTime / daysMillisecond;
        //相差小时数 = (总的毫秒值 - 天数 * 一天的时间毫秒值) / (一小时的时间毫秒值)
        long hours = (diffTime - days * daysMillisecond) / hoursMillisecond;
        //相差分钟数 = (总的毫秒值 - 天数 * 一天的时间毫秒值 - 小时数 * 一小时的时间毫秒值) / (一分钟的时间毫秒值)
        long minutes = (diffTime - days * daysMillisecond - hours * hoursMillisecond) / minutesMillisecond;
        //相差秒数 = (总的毫秒值 - 天数 * 一天的时间毫秒值 - 小时数 * 一小时的时间毫秒值 - 分钟数 * 一分钟的事件毫秒值) / 一秒的时间毫秒值
        //long second = (diffTime - days * daysMillisecond - hours * hoursMillisecond - minutes * minutesMillisecond) / millisecond;

        if (minutes > 0) {
            hours ++;
        }

        //String stringDate = days + "天" + hours + "小时" + minutes + "分钟" + second + "秒";
        return hours;
    }
}
