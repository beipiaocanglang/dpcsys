package dpcsys.consumption.frame.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @version V1.0
 * @Description: web层使用对象查询
 * @Company:Justin
 * @author: lijianjun
 * @date: 2015年11月9日 下午2:27:03
 */
public class BaseController {
    protected transient final Logger logger = Logger.getLogger(this.getClass());

    /**
     * 读取日志对象
     **/
    public Logger getLogger() {
        return logger;
    }

    /**
     * request
     **/
    private HttpServletRequest request;
    /**
     * response
     **/
    private HttpServletResponse response;
    /**
     * session
     **/
    private HttpSession session;

    /**
     * 获取request
     **/
    public HttpServletRequest getRequest() {
        return request;
    }

    /**
     * 获取response
     **/
    public HttpServletResponse getResponse() {
        return response;
    }

    /**
     * 获取session
     **/
    public HttpSession getSession() {
        return session;
    }

    /**
     * 解决controller中获取request/response,不需要在参数中显示定义
     *
     * @param request
     * @param response
     * @method: setReqAndRes
     * @Description: TODO
     * @author : lijianjun
     * @date 2017年4月30日 下午6:20:32
     */
    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession();
    }
}
