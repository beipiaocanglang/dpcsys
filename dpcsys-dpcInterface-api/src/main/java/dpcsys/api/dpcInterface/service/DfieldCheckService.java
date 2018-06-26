
package dpcsys.api.dpcInterface.service;

import java.util.List;
import java.util.Map;

import dpcsys.api.dpcInterface.model.DfieldCheck;
import faner.dplatformSpringjdbc.api.frame.service.GenericNamedService;
import faner.dplatformSpringjdbc.api.frame.util.tools.page.PageBean;

/**
 * @version V2.0
 * @Company:faner
 * @author: lijianjun
 */
public interface DfieldCheckService extends GenericNamedService<DfieldCheck, java.lang.Long> {

    /**
     * 查询所有,根据不定参数
     *
     * @return 数据集合
     * @method: findByObj
     * @Description: 查询所有, 根据不定参数
     * @paramsMap 参数
     * @author: lijianjun
     */
    List<DfieldCheck> findByObj(Map<String, Object> paramsMap);

    /**
     * 查询所有,根据不定参数,分页查询
     *
     * @return 数据集合
     * @method: findByPageObj
     * @Description: 查询所有, 根据不定参数
     * @paramsMap fenye
     * @page 分页信息参数
     * @author: lijianjun
     */
    List<Map<String, Object>> findListMapByObj(Map<String, Object> paramsMap);

    /**
     * 查询所有,根据不定参数,分页查询
     *
     * @return 数据集合
     * @method: findByPageObj
     * @Description: 查询所有, 根据不定参数
     * @paramsMap fenye
     * @page 分页信息参数
     * @author: lijianjun
     */
    List<DfieldCheck> findByPageObj(Map<String, Object> paramsMap, PageBean page);

    /**
     * 查询所有,根据不定参数,分页查询
     *
     * @return 数据集合
     * @method: findByPageObj
     * @Description: 查询所有, 根据不定参数
     * @paramsMap fenye
     * @page 分页信息参数
     * @author: lijianjun
     */
    List<Map<String, Object>> findListMapByPageObj(Map<String, Object> paramsMap, PageBean page);

    /**
     * 查询条数,根据不定参数
     *
     * @return 数据
     * @method: findByObj
     * @Description: 查询条数, 根据不定参数
     * @paramsMap 参数
     * @author: lijianjun
     */
    Map<String, Object> count(Map<String, Object> paramsMap);
}