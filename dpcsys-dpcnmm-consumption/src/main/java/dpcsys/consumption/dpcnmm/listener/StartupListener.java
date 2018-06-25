package dpcsys.consumption.dpcnmm.listener;


import javax.servlet.ServletContextEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ContextLoaderListener;

/**
 * @author : lijianjun
 * @version V1.0
 * @Description: extends “ContextLoaderListener”
 *      loading...
 *          1.config.properties
 *          2.permission and mnue
 * @Company:Justin
 * @date: 2015年11月16日 上午10:25:47
 */
public class StartupListener extends ContextLoaderListener {

    private static Log logger = LogFactory.getLog(StartupListener.class);

    public void contextInitialized(ServletContextEvent event) {

        super.contextInitialized(event);
        //Initialize the implemental Bean of the Application
        DpcnmmAppUtil.init(event.getServletContext());

        logger.info("-------ApplicationContext.xml is loaded!!!");
    }
}
