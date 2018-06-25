package dpcsys.api.frame.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import dpcsys.api.frame.util.FrameAppUtilAPI;

/**
 * 
 * @Description: 方便取得Spring容器，取得其他服务实例，但必须在Spring的配置文件里进行配置 如：
* 				<bean id="appUtil" class="...util.core.AppUtil"/> 也提供整个应用程序的相关配置获取方法
 * @Company:Justin
 * @author : lijianjun
 * @date: 2015年11月16日 上午10:26:25
 * @version V1.0
 */
@SuppressWarnings({"static-access"})
public abstract class AppUtil extends FrameAppUtilAPI implements ApplicationContextAware {
	
	/**① 
	 * @method: setApplicationContext
	 * @Description: overwritten “ApplicationContextAware”
	 *               ①function:get the Bean From the IOC container / Get the Bean from Spring IOC
	 *
	 * @param applicationContext
	 * @throws BeansException
	 *
	 * @author: lijianjun
	 * @date 2014-1-2 下午01:35:39
	 */
	private static ApplicationContext appContext;
	
	public static void setAppContext(ApplicationContext appContext) {
		AppUtil.appContext = appContext;
	}
	
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		AppUtil.setAppContext(applicationContext);
	}
	private static void checkApplicationContext() {
		if (appContext == null) {
			throw new IllegalStateException("applicaitonContext未注入,请在app-resource.xml中定义AppUtil");
		}
	}
	public static Object getBean(String beanId) {
		checkApplicationContext();
		return appContext.getBean(beanId);
	}

	//一、Application global object
	private static ServletContext servletContext = null;
	
	public static ServletContext getServletContext(){
		return servletContext;
	}
	
	public static void setServletContext(ServletContext context){
		servletContext = context;
	}
	
	//get Absolute path of the Application
	public static String getAppAbsolutePath() {	
		return servletContext.getRealPath("/");
	}
	//二、Deposit the configuration of the "config.properties"（①Don`t need to use cache）
	

//********************************Up: System start loading(save RAM)*********************************
	
	/**
	 * 取得流程表单模板的目录的绝对路径
	 * 
	 * @return
	 */
	public String getFlowFormAbsolutePath() {
		String path = (String) this.getSysConfig().get("app.flowFormPath");
		if (path == null)
			path = "/WEB-INF/FlowForm/";
		return getAppAbsolutePath() + path;

	}
	
    /**
    * @method: getIpAddr
    * @Description: 探测访问机的真实IP
    *
    * @param request
    * @return
    * @return String
    *
    * @author: lijianjun
    * @date 2013-10-14 上午10:43:32
    */
    public static String getIpAddr(HttpServletRequest request) {  
       String ip = request.getHeader("x-forwarded-for");      
       if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
           ip = request.getHeader("Proxy-Client-IP");  
       }  
       if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
           ip = request.getHeader("WL-Proxy-Client-IP");  
       }  
       if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
           ip = request.getHeader("HTTP_CLIENT_IP");  
       }  
       if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
           ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
       }  
       if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
           ip = request.getRemoteAddr();  
       } 
       if (ip == null || ip.length() == 0 || ip.split("\\.").length != 4)
       {
    	   ip = "127.0.0.1";
       }
	   return ip;
	} 
}
