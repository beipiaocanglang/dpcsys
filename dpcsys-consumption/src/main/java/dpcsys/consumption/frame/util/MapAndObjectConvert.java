package dpcsys.consumption.frame.util;

import org.apache.commons.beanutils.BeanMap;

import java.util.Map;

import static org.apache.commons.beanutils.BeanUtils.populate;

/**
 * map 与 object 之间相互转化
 * author : sunpanhu
 * create time : 2018/5/11 下午4:37
 */
public class MapAndObjectConvert {
    /**
     * map 转 object
     * 使用org.apache.commons.beanutils进行转换
     * @param map
     * @param beanClass
     * @return
     * @throws Exception
     */
    public static Object mapToObj(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null){
            return null;
        }

        Object obj = beanClass.newInstance();

        populate(obj, map);

        return obj;
    }
    /**
     * object 转 map
     * 使用org.apache.commons.beanutils进行转换
     * @param obj
     * @return
     */
    public static Map<?, ?> objToMap(Object obj) {
        if(obj == null){
            return null;
        }

        Map<?, ?> beanMap = new BeanMap(obj);

        return beanMap;
    }
}
