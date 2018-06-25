package dpcsys.consumption.dpcInterface.basic.plugins;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import dpcsys.consumption.dpcInterface.listener.DpcInterfaceAppUtil;
import faner.dplatformSpringjdbc.api.frame.util.tools.date.DateCoreUtil;
import faner.dplatformSpringjdbc.api.frame.util.tools.json.JsonCoreUtil;
import faner.dplatformSpringjdbc.api.frame.util.tools.object.ListCoreUtil;
import faner.dplatformSpringjdbc.api.frame.util.tools.object.StringCoreUtil;

/**
 * 请求AOP
 *
 * @author : lijianjun
 * @version V1.0
 * @Description: 可以很好的记录日志，其中可以有参数、请求地址等
 * @Company:
 * @date: 2016年9月2日 上午8:52:15
 */
public class APIHandlerInterceptor implements HandlerInterceptor {

    /**
     * 日志
     */
    private Logger logger = Logger.getLogger(this.getClass());

    /**
     * preHandle方法是进行处理器拦截用的，顾名思义，该方法将在Controller处理之前进行调用，
     * SpringMVC中的Interceptor拦截器是链式的，可以同时存在
     * 多个Interceptor，然后SpringMVC会根据声明的前后顺序一个接一个的执行
     * ，而且所有的Interceptor中的preHandle方法都会在
     * Controller方法调用之前调用。SpringMVC的这种Interceptor链式结构也是可以进行中断的
     * ，这种中断方式是令preHandle的返 回值为false，当preHandle的返回值为false的时候整个请求就结束了。
     *
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, String> maps = new HashMap<String, String>();
        maps.put("code", new Date().getTime() + "");
        maps.put("begin_date", DateCoreUtil.formatCnDateYMDHMSSS(new Date()));
        maps.put("URL", request.getRequestURL().toString().replace('\\', '/'));
        maps.put("IP", getRemoteHost(request));
        maps.put("param", request.getParameterMap() != null ? JsonCoreUtil.toJSONString(request.getParameterMap()) : null);
        logger.info("Processing before calling API,time=" + DateCoreUtil.formatCnDateYMDHMS(new Date()));
        String url = request.getRequestURL().toString();
        logger.info("API-URL:" + url);

//		ResponseHashMap responseMap = new ResponseHashMap();
        // 检查参数
//		boolean boo = checkParam(request);
//		if(!boo){
//			// 终止执行
//			responseMap = new ResponseHashMap(ResponseConstant.CODE_PARAM_ERROR,ResponseConstant.MSG_PARAM_ERROR);
//			JsonCoreUtil.outPutJson(response, JsonCoreUtil.toJSONString(responseMap));
//		}
//		return boo;
        return true;
    }

    /**
     * 这个方法只会在当前这个Interceptor的preHandle方法返回值为true的时候才会执行。postHandle是进行处理器拦截用的，
     * 它的执行时间是在处理器进行处理之后，也就是在Controller的方法调用之后执行，但是它会在DispatcherServlet进行视图的渲染之前执行
     * ，也就是说在这个方法中你可以对ModelAndView进行操作。这个方法的链式结构跟正常访问的方向是相反的，也就是说先声明的Interceptor拦截器该方法反而会后调用
     * ，这跟Struts2里面的拦截器的执行过程有点像，只是Struts2里面的intercept方法中要手动的调用ActionInvocation的invoke方法
     * ，Struts2中调用ActionInvocation的invoke方法就是调用下一个Interceptor
     * 或者是调用action，然后要在Interceptor之前调用的内容都写在调用invoke之前，要在Interceptor之后调用的内容都写在调用invoke方法之后。
     */
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    /**
     * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行。该方法将在整个请求完成之后，
     * 也就是DispatcherServlet渲染了视图执行，
     * 这个方法的主要作用是用于清理资源的，当然这个方法也只能在当前这个Interceptor的preHandle方法的返回值为true时才会执行。
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        Map<String, String> maps = new HashMap<String, String>();
        maps.put("code", new Date().getTime() + "");
        maps.put("end_date", DateCoreUtil.formatCnDateYMDHMSSS(new Date()));
        maps.put("URL", request.getRequestURL().toString().replace('\\', '/'));
        maps.put("IP", getRemoteHost(request));
        maps.put("param", request.getParameterMap() != null ? JsonCoreUtil.toJSONString(request.getParameterMap()) : null);
        logger.info("Call API after processing,time=" + DateCoreUtil.formatCnDateYMDHMS(new Date()));
    }

    /**
     * 检查参数
     *
     * @param request
     * @return
     * @throws Exception
     * @method: checkParam
     * @Description: TODO
     * @author : lijianjun
     * @date 2017年3月22日 下午7:45:01
     */
    @SuppressWarnings({"rawtypes", "unused"})
    private boolean checkParam(HttpServletRequest request) throws Exception {
        boolean boo = false;
        // 获取所属业务标识
        String ownedBusiness = request.getRequestURI();
        ownedBusiness = ownedBusiness.substring(1, ownedBusiness.length());
        ownedBusiness = ownedBusiness.substring(ownedBusiness.indexOf("/") + 1, ownedBusiness.length());
        if (StringCoreUtil.isNotEmpty(ownedBusiness)) {
            /** 获取数据 */
            List<Map> dfieldCheckList = DpcInterfaceAppUtil.getDdFieldCheckMap(ownedBusiness);
            if (ListCoreUtil.isNotEmpty(dfieldCheckList)) {
                // 取得reques 请求参数
                String jsonStringObject = JsonCoreUtil.toJSONString(request.getParameterMap());
                if (jsonStringObject != null) {
                    // 对参数进行解码，因为js中的get方式提交中文会出现乱码情况
                    jsonStringObject = URLDecoder.decode(jsonStringObject, "UTF-8");
                }
//				System.out.println("请求参数：" + jsonStringObject);
                // 取得该业务下的所有字段校验
                JSONArray jsonDataArr = JsonCoreUtil.toJSONArray(dfieldCheckList);
                // 封装成map
                Map<String, String> dataMap = AssembleArrToMap(jsonDataArr);
                // 取得传到到消费端的参数
                Map<String, String> mapParam = getParam(jsonStringObject);
                // 开始校验参数
                return checkParam(dataMap, mapParam);
            } else {
                boo = true;
            }
        } else {
            String requestUrl = request.getRequestURL().toString();
            if (requestUrl.contains("/login.do")
                    || requestUrl.contains(".html")
                    || requestUrl.contains(".css")
                    || requestUrl.contains(".js")
                    || requestUrl.contains(".png")
                    || requestUrl.contains(".jpg")
                    || requestUrl.contains(".woff")
                    || requestUrl.contains(".ttf")
                    ) {
                boo = true;
            }
        }
        return boo;
    }

    /**
     * 组装集合到map中
     *
     * @param jsonDataArr
     * @return
     * @method: AssembleArrToMap
     * @Description: TODO
     * @author : lijianjun
     * @date 2016年11月3日 上午11:34:56
     */
    private Map<String, String> AssembleArrToMap(JSONArray jsonDataArr) {
        Map<String, String> map = new HashMap<>();
        JSONObject jsonObj = null;
        for (int i = 0; i < jsonDataArr.size(); i++) {
            jsonObj = jsonDataArr.getJSONObject(i);
            map.put(jsonObj.getString("name"), jsonObj.getString("regular"));
        }
        return map;
    }

    /**
     * 获取请求到消费端的参数，封装map
     *
     * @param jsonString
     * @return
     * @throws UnsupportedEncodingException
     * @method: getParam
     * @Description: TODO
     * @author : lijianjun
     * @date 2016年11月3日 上午11:36:17
     */
    @SuppressWarnings("rawtypes")
    public Map<String, String> getParam(String jsonString) throws UnsupportedEncodingException {
        // 提交的消费端的参数
        Map<String, String> mapParam = null;
        mapParam = new HashMap<String, String>();
        // 对参数进行解码，因为js中的get方式提交中文会出现乱码情况
        jsonString = URLDecoder.decode(jsonString, "UTF-8");
        JSONObject jsonObject = JsonCoreUtil.toJSONObject(jsonString);
        if (jsonObject != null) {
            Iterator iterator = jsonObject.keys();
            String key = "";
            String value = "";
            while (iterator.hasNext()) {
                key = (String) iterator.next();
                value = JsonCoreUtil.toJSONArray(jsonObject.getString(key)).get(0).toString();
                mapParam.put(key, value);
            }
            return mapParam;
        }
        return null;
    }

    /**
     * 检查参数
     *
     * @param dataMap  数据库参数
     * @param mapParam 传递参数
     * @return
     * @method: checkParam
     * @Description: TODO
     * @author : lijianjun
     * @date 2016年11月3日 上午11:41:05
     */
    public boolean checkParam(Map<String, String> dataMap, Map<String, String> mapParam) {
//		System.out.println("数据库参数：" + JsonCoreUtil.toJSONString(dataMap));
//		System.out.println("请求参数：" + JsonCoreUtil.toJSONString(mapParam));
        boolean boo = true;
        // 参数key
        String key = "";
        // 参数值
        String value = "";
        // 参数key的正则表达式
        String regular = "";
        for (Map.Entry<String, String> entry : dataMap.entrySet()) {
            key = entry.getKey();
            regular = entry.getValue();
            value = mapParam != null && StringCoreUtil.isNotEmpty(mapParam.get(key)) ? mapParam.get(key) : "";
            if (!Pattern.matches(regular, value)) {
                boo = false;
                System.out.println("参数：" + key + ",值：" + (StringCoreUtil.isNotEmpty(value) ? value : "NULL") + "。未通过正则“" + regular + "”校验！");
                break;
            }
        }
        return boo;
    }

    /**
     * 读取request消息头
     *
     * @param request
     * @return
     * @method: getHeadersInfo
     * @Description: TODO
     * @author : lijianjun
     * @date 2017年3月22日 下午7:46:35
     */
    @SuppressWarnings({"unused", "rawtypes"})
    private Map<String, String> getHeadersInfo(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

    /**
     * 读取IP
     *
     * @param request
     * @return
     * @method: getRemoteHost
     * @Description: TODO
     * @author : lijianjun
     * @date 2017年3月22日 下午7:45:35
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
