package dpcsys.api.frame.util.converter;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.core.convert.converter.Converter;

public class DateConverter implements Converter<String, Date>{
	
	protected transient final Logger logger = Logger.getLogger(this.getClass());
	
	private static final String[] PATTERNS = {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "HH:mm:ss"};
	
	/**
	 * 日期类型转换器，解决了画面传入的日期类型，接受不到以及格式不正确问题
	 * @method: convert
	 * @Description: TODO
	 * @param s
	 * @return
	 * @author : lijianjun
	 * @date 2017年6月22日 下午5:54:36
	 */
	@Override
    public Date convert(String s) {
        if(!StringUtils.isBlank(s)) {
            try {
                return DateUtils.parseDate(s, PATTERNS);
            } catch (Exception e) {
            	logger.error("日期转换异常：[" + s + "]",e);
            }
        }
        return null;
    }
}