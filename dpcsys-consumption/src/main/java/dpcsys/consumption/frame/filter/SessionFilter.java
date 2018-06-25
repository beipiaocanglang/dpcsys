package dpcsys.consumption.frame.filter;

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

import dpcsys.api.frame.listener.AppUtil;
import dpcsys.consumption.frame.exception.IllegalUrlException;
import dpcsys.consumption.frame.exception.SessionTimeException;
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

        try {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            HttpServletRequest httpRequest = (HttpServletRequest) request;
//			HttpSession session = httpRequest.getSession();
            // 过滤URL 是否已经登录
            String url = httpRequest.getRequestURL().toString();

            // 设置请求消息头
            String uploadUrl = AppUtil.getSysConfig().get("uploadUrl");
            if (StringCoreUtil.isNotEmpty(uploadUrl) && url.contains(uploadUrl)) {
                String access = AppUtil.getSysConfig().get("Access-Control-Allow-Origin");
                if (StringCoreUtil.isNotEmpty(access)) {
                    String[] tempAccess = access.split(",");
                    for (String str : tempAccess) {
                        httpResponse.setHeader("Access-Control-Allow-Origin", str);
                    }
                    httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
                    httpResponse.setHeader("Access-Control-Max-Age", "3600");
                    httpResponse.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization");
                    httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
                }
            }

            if (url.contains("/index.jsp")
                    || url.contains("login/index")
                    || url.contains("/css/")
                    || url.contains("/js/")
                    || url.contains("login/index")
                    || url.contains("/frame/login")
                    || url.contains("/login/login")
                    || url.contains("/images/")
                    || url.contains("/randomImage")
                    || url.contains("/common/")
                    || url.contains("frame/up_ys_order")
                    || url.endsWith("/")
                    ) {
                chain.doFilter(httpRequest, httpResponse);
            } else {
                chain.doFilter(httpRequest, httpResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

    }

    public void init(FilterConfig filterConfig) throws ServletException {

    }

}