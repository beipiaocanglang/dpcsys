package dpcsys.consumption.frame.util;

import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期類型轉 string類型
 * author: canglang
 * create time: 2018/5/24 23:09
 */
public class DateToString implements Converter<Date, String> {
    public String convert(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {

            return simpleDateFormat.format(date);
        } catch (Exception ex) {
            return null;
        }
    }
}
