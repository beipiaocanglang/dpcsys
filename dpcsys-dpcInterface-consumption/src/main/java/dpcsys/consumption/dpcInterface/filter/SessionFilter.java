package dpcsys.consumption.dpcInterface.filter;

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

import dpcsys.api.dpcInterface.constants.DpcInterfaceResponseConstant;
import dpcsys.api.dpcInterface.constants.DpcInterfaceResponseHashMap;
import dpcsys.consumption.dpcInterface.listener.DpcInterfaceAppUtil;
import dpcsys.consumption.frame.exception.IllegalUrlException;
import dpcsys.consumption.frame.exception.SessionTimeException;
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

    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException, SessionTimeException, IllegalUrlException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        try {
            //String url = httpRequest.getRequestURL().toString();
            // 解决跨域问题
            // 设置请求消息头
//			String uploadUrl = DpcInterfaceAppUtil.getSysConfig().get("uploadUrl");
//			if(StringCoreUtil.isNotEmpty(uploadUrl) && url.contains(uploadUrl)){
            String access = DpcInterfaceAppUtil.getSysConfig().get("Access-Control-Allow-Origin");
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
//			}
            chain.doFilter(httpRequest, httpResponse);
//			String url = httpRequest.getRequestURL().toString();
//			System.out.println("filter-url: " + url);
//			if(
//					url.contains("sysUser/login")
//					|| url.contains("sysUser/logout")
//					|| url.contains("sysUser/registered")
//					|| url.contains("sysUser/sendShotMsg")
//					|| url.contains("sysUser/getPassWord")
//					|| url.contains("sysUser/kaptcha")
//					|| url.contains("uploadFile/uploads")
//					){
//				chain.doFilter(httpRequest, httpResponse);
//			}else{
//				// 验证是否登录超时
//				// 默认不通过
//				boolean boo = false;
//				// 首先获取参数中的登录用户名
//				String curLoginName = httpRequest.getParameter("curLoginName");
//				if(!StringCoreUtil.isNotEmpty(curLoginName)){
//					curLoginName = httpRequest.getAttribute("curLoginName") != null ? httpRequest.getAttribute("curLoginName").toString() : null;
//				}
//				if(StringCoreUtil.isNotEmpty(curLoginName)){
//					// 读取redis用户信息
//					String userJson = redisService.getStrValue(curLoginName);
//					if(StringCoreUtil.isNotEmpty(userJson)){
//						redisService.add(curLoginName, userJson, Long.valueOf(ResponseConstant.DEFAULT_SESSION_INT_TIME));
//						boo = true;
//					}
//				}
//				if(boo){
//					chain.doFilter(httpRequest, httpResponse);
//				}else{
//					JuDuSysResponseHashMap map = new JuDuSysResponseHashMap(ResponseConstant.CODE_SESSION_ERROR,ResponseConstant.MSG_SESSION_ERROR);
//					JsonCoreUtil.outPutJson(httpResponse, JsonCoreUtil.toJSONString(map));
//				}
//			}
        } catch (Exception e) {
            DpcInterfaceResponseHashMap map = new DpcInterfaceResponseHashMap(DpcInterfaceResponseConstant.CODE_SYSTEM_ERROR, DpcInterfaceResponseConstant.MSG_SYSTEM_ERROR);
            JsonCoreUtil.outPutJson(httpResponse, JsonCoreUtil.toJSONString(map));
            e.printStackTrace();
            logger.error(e.getMessage());
        }

    }

    // 初始化加载spring bean，为了注入
    public void init(FilterConfig filterConfig) throws ServletException {
//		ApplicationContext ac =  
//                WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext());
//		if(redisService == null){
//			redisService = (RedisService) ac.getBean("redisService");
//		}
    }
}