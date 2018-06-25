package dpcsys.consumption.dpcnmm.basic.plugins;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import dpcsys.api.dpcnmm.constants.DpcnmmResponseConstant;
import dpcsys.api.dpcnmm.constants.DpcnmmResponseHashMap;
import faner.dplatformSpringjdbc.api.frame.util.tools.date.DateCoreUtil;
import faner.dplatformSpringjdbc.api.frame.util.tools.json.JsonCoreUtil;


/**
 * 统一异常处理
 *
 * @author : lijianjun
 * @version V1.0
 * @Description: 统一异常处理
 * @Company:Justin
 * @date: 2016年7月7日 上午11:40:01
 */
public class UnifiedExceptionHandler implements HandlerExceptionResolver {

    public static final Logger logger = Logger.getLogger(UnifiedExceptionHandler.class);

    /**
     * 统一异常处理模块
     *
     * @param request
     * @param response
     * @param handler
     * @param ex 异常
     * @return 页面
     * @method: resolveException 统一异常处理模块
     * @Description: 统一异常处理模块
     * @author: lijianjun
     * @date 2016年4月27日 上午11:39:04
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception ex) {
        Map<String, String> maps = new HashMap<String, String>();
        DpcnmmResponseHashMap responseMap = new DpcnmmResponseHashMap(DpcnmmResponseConstant.CODE_SYSTEM_ERROR, DpcnmmResponseConstant.MSG_SYSTEM_ERROR);
        try {
            logger.error("ip:host--->" + getRemoteHost(request));
            maps.put("code", new Date().getTime() + "");
            maps.put("begin_date", DateCoreUtil.formatCnDateYMDHMSSS(new Date()));
            maps.put("URL", request.getRequestURL().toString().replace('\\', '/'));
            maps.put("IP", getRemoteHost(request));
            maps.put("param", request.getParameterMap() != null ? JsonCoreUtil.toJSONString(request.getParameterMap()) : null);
            logger.error("parameter--->" + JsonCoreUtil.toJSONString(maps));
            logger.error("ex ： {}", ex);
            JsonCoreUtil.outPutJson(response, JsonCoreUtil.toJSONString(responseMap));
            return null;
        } catch (Exception e) {
            maps.put("code", new Date().getTime() + "");
            maps.put("begin_date", DateCoreUtil.formatCnDateYMDHMSSS(new Date()));
            maps.put("URL", request.getRequestURL().toString().replace('\\', '/'));
            maps.put("IP", getRemoteHost(request));
            maps.put("ex", ex.toString());
            maps.put("param", request.getParameterMap() != null ? JsonCoreUtil.toJSONString(request.getParameterMap()) : null);
            maps.put("URL", request.getRequestURL() != null ? request.getRequestURL().toString() : null);
            logger.error("[UnifiedExceptionHandler.resolveException]:errorInfo---->" + JsonCoreUtil.toJSONString(maps));
            logger.error("error {}", e);
            JsonCoreUtil.outPutJson(response, JsonCoreUtil.toJSONString(responseMap));
            return null;
        }
    }

    /**
     * 获取IP
     *
     * @param request
     * @return
     * @method: getRemoteHost
     * @Description: 获取请求IP
     * @author : lijianjun
     * @date 2016年9月2日 上午8:59:07
     */
    public String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }
}
