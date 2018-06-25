package dpcsys.consumption.dpcnmm.listener;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import dpcsys.api.dpcnmm.model.DfieldCheck;
import dpcsys.api.dpcnmm.service.DfieldCheckService;
import dpcsys.api.frame.listener.AppUtil;
import faner.dplatform.api.frame.redis.JedisService;
import faner.dplatformSpringjdbc.api.frame.util.tools.json.JsonCoreUtil;
import faner.dplatformSpringjdbc.api.frame.util.tools.object.ListCoreUtil;
import faner.dplatformSpringjdbc.api.frame.util.tools.object.StringCoreUtil;

/**
 * @author : lijianjun
 * @version V1.0
 * @Description: 方便取得Spring容器，取得其他服务实例，但必须在Spring的配置文件里进行配置 如：
 * <bean id="appUtil" class="...util.core.AppUtil"/> 也提供整个应用程序的相关配置获取方法
 * @Company:Justin
 * @date: 2015年11月16日 上午10:26:25
 */
@SuppressWarnings({"rawtypes"})
public class DpcnmmAppUtil extends AppUtil {

    private static Logger logger = Logger.getLogger(DpcnmmAppUtil.class);

    @Autowired(required = false)
    private DfieldCheckService dfieldCheckService;
    // redis服务
    @Autowired(required = false)
    private JedisService jedisService;

    /**
     * ②
     *
     * @param in_servletContext
     * @return void
     * @method: init
     * @Description: Load static/system data when WEB application server startup
     * @author: lijianjun
     * @date 2013-10-23 上午09:16:32
     */
    public static void init(ServletContext in_servletContext) {
        setServletContext(in_servletContext);

        // 1.读取来自config.properties文件的配置,并且放入configMap内,应用程序共同使用
        String filePath = getServletContext().getRealPath("/WEB-INF/classes/conf/");
        String configFilePath = filePath + "/config.properties";

        Properties props = new Properties();
        DpcnmmAppUtil dpcnmmAppUtil = (DpcnmmAppUtil) getBean("dpcnmmAppUtil");
        try {
            FileInputStream fis = new FileInputStream(configFilePath);
            Reader r = new InputStreamReader(fis, "UTF-8");
            props.load(r);

            Iterator it = props.keySet().iterator();
            while (it.hasNext()) {
                String key = (String) it.next();
                getSysConfig().put(key, props.get(key) != null ? props.get(key).toString() : "");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

        //2.load :permission、dictionary and mune
        dpcnmmAppUtil.load();

    }


    /**
     * ③
     *
     * @return void
     * @method: load
     * @Description: load- menu, params, permission
     * @author lijianjun
     */
    public void load() {
        try {
            // 加载权限
//		    privilageManager.loadAllPrivilege();
            //一、Loading permission(include:role--list[menu+operator])
//			sysAuthorityService.loadPermissionToRAM();	

            //二、Loading the system parameters TODO
//			sysParametersService.findLoad();
//			System.out.println(JsonCoreUtil.toJSONString(getSysConfig()));

            // 三、加载自动校验数据
            loadAllDFieldCheckMap();

            // 加载数据字典数据
//			loadSysParameters();
            System.out.println("正在调用监听器中的load方法............");
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage());
        }
    }

//********************************Up: System start loading(save RAM)*********************************

    /**
     * 字段校验所有数据，key:业务表示，value：数据对象
     **/
    private static Map<String, List<Map>> dFieldCheckMap = new HashMap<String, List<Map>>();

    /**
     * 根据业务读取业务校验数据集合
     *
     * @param ownedBusiness 业务标识
     * @return
     * @method: getDdFieldCheckMap
     * @Description: TODO
     * @author : lijianjun
     * @date 2017年3月20日 下午6:18:39
     */
    public static List<Map> getDdFieldCheckMap(String ownedBusiness) {
        if (StringCoreUtil.isNotEmpty(ownedBusiness)) {
            return dFieldCheckMap.get(ownedBusiness);
        }
        return null;
    }

    /**
     * 加载自动校验数据
     *
     * @method: loadAllDFieldCheckMap
     * @Description: TODO
     * @author : lijianjun
     * @date 2017年3月20日 下午6:19:05
     */
    public void loadAllDFieldCheckMap() {
        List<DfieldCheck> dfieldCheckList = dfieldCheckService.findAll();
        if (ListCoreUtil.isNotEmpty(dfieldCheckList)) {
            // 默认赋值第一个
            String tempOwnedBusiness = dfieldCheckList.get(0).getOwnedBusiness();
            List<Map> tempList = new ArrayList<Map>();
            for (DfieldCheck dfieldCheck : dfieldCheckList) {
                if (tempOwnedBusiness.equals(dfieldCheck.getOwnedBusiness())) {
                    tempList.add(JsonCoreUtil.toHashMap(dfieldCheck));
                } else {
                    dFieldCheckMap.put(tempOwnedBusiness, tempList);
                    tempOwnedBusiness = dfieldCheck.getOwnedBusiness();
                    tempList = new ArrayList<Map>();
                    tempList.add(JsonCoreUtil.toHashMap(dfieldCheck));
                }
            }
            // 补录最后一组
            dFieldCheckMap.put(tempOwnedBusiness, tempList);
        }
    }

}
