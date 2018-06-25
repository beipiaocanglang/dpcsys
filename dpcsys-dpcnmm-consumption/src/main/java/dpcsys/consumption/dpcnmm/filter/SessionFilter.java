package dpcsys.consumption.dpcnmm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import dpcsys.api.dpcnmm.constants.DpcnmmResponseConstant;
import dpcsys.api.dpcnmm.constants.DpcnmmResponseHashMap;
import dpcsys.consumption.dpcnmm.listener.DpcnmmAppUtil;
import dpcsys.consumption.frame.exception.IllegalUrlException;
import dpcsys.consumption.frame.exception.SessionTimeException;
import faner.dplatform.api.frame.redis.RedisService;
import faner.dplatformSpringjdbc.api.frame.util.tools.json.JsonCoreUtil;
import faner.dplatformSpringjdbc.api.frame.util.tools.object.StringCoreUtil;

/**
 * @author : lijianjun
 * @version V1.0
 * @Description: Seesion out filter
 * @Company:Justin
 * @date: 2015年11月16日 上午10:23:10
 */
public class SessionFilter implements Filter {
    private static Logger logger = Logger.getLogger(SessionFilter.class);

    private RedisService redisService;

    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException, SessionTimeException, IllegalUrlException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        try {
            // 解决跨域问题
            // 设置请求消息头
            String access = DpcnmmAppUtil.getSysConfig().get("Access-Control-Allow-Origin");
            if (StringCoreUtil.isNotEmpty(access)) {
                String[] tempAccess = access.split(",");
                for (String str : tempAccess) {
                    httpResponse.setHeader("Access-Control-Allow-Origin", str);
                }
                httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
                httpResponse.setHeader("Access-Control-Max-Age", "86400");
                httpResponse.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization");
                httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            }
            String url = httpRequest.getRequestURL().toString();
            if (url.contains("dpcLogin/login") || url.contains("dpcLogin/logout") || url.contains("dpcLogin/kaptcha") || url.contains("uploadFile/uploads")
                    || url.contains("index.html") || url.contains(".css") || url.contains(".js") || url.contains("static/css") || url.contains("static/js")
                    || url.contains("static/img") || url.contains("static/*") || url.contains("/api-docs")) {
                chain.doFilter(httpRequest, httpResponse);
            } else {
                // 验证是否登录超时
                // 默认不通过
                boolean boo = true;
                // 首先获取参数中的登录用户名
//				String curLoginName = httpRequest.getParameter("curLoginName");
//				if(!StringCoreUtil.isNotEmpty(curLoginName)){
//					curLoginName = httpRequest.getAttribute("curLoginName") != null ? httpRequest.getAttribute("curLoginName").toString() : null;
//				}
//				if(StringCoreUtil.isNotEmpty(curLoginName)){
//					// 读取redis用户信息
//					String userJson = redisService.getStrValue(curLoginName);
//					if(StringCoreUtil.isNotEmpty(userJson)){
//						redisService.add(curLoginName, userJson, Long.valueOf(DpcnmmResponseConstant.DEFAULT_SESSION_INT_TIME));
//						boo = true;
//					}
//				}
                if (boo) {
//					DpcnmmResponseHashMap map = new DpcnmmResponseHashMap(DpcnmmResponseConstant.CODE_SESSION_ERROR,DpcnmmResponseConstant.MSG_SESSION_ERROR);
//					JsonCoreUtil.outPutJson(httpResponse, JsonCoreUtil.toJSONString(map));
                    chain.doFilter(httpRequest, httpResponse);
                } else {
                    DpcnmmResponseHashMap map = new DpcnmmResponseHashMap(DpcnmmResponseConstant.CODE_SYSTEM_ERROR, DpcnmmResponseConstant.MSG_SYSTEM_ERROR);
                    JsonCoreUtil.outPutJson(httpResponse, JsonCoreUtil.toJSONString(map));
                }
            }
        } catch (Exception e) {
            DpcnmmResponseHashMap map = new DpcnmmResponseHashMap(DpcnmmResponseConstant.CODE_SYSTEM_ERROR, DpcnmmResponseConstant.MSG_SYSTEM_ERROR);
            JsonCoreUtil.outPutJson(httpResponse, JsonCoreUtil.toJSONString(map));
            e.printStackTrace();
            logger.error(e.getMessage());
        }

    }

    // 初始化加载spring bean，为了注入
    public void init(FilterConfig filterConfig) throws ServletException {
        ApplicationContext ac =
                WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext());
        if (redisService == null) {
            redisService = (RedisService) ac.getBean("redisService");
        }
    }
}