package dpcsys.core.dpcnmm.service.impl;

import dpcsys.api.dpcnmm.dao.DfieldCheckDao;
import dpcsys.api.dpcnmm.model.DfieldCheck;
import dpcsys.api.dpcnmm.service.DfieldCheckService;
import faner.dplatformSpringjdbc.api.frame.command.db.springJDBC.xml.OverallSituationStatic;
import faner.dplatformSpringjdbc.api.frame.util.tools.page.PageBean;
import faner.dplatformSpringjdbc.api.frame.util.tools.velocity.VelocityCoreUtils;
import faner.dplatformSpringjdbc.core.frame.service.impl.GenericNamedServiceImpl;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

/**
 * @version V2.0
 * @author: lijianjun
 */
@SuppressWarnings("serial")
public class DfieldCheckServiceImpl extends GenericNamedServiceImpl<DfieldCheck, java.lang.Long> implements DfieldCheckService {

    private DfieldCheckDao dao;

    public DfieldCheckServiceImpl(DfieldCheckDao dao) {
        super(dao);
        this.dao = dao;
    }

    @SuppressWarnings("unused")
    private Logger log = Logger.getLogger(this.getClass());

    public List<DfieldCheck> findByObj(Map<String, Object> paramsMap) {
        Map<String, String> sqlMap = OverallSituationStatic.getTableAllSql(DfieldCheck.class.getSimpleName());
        return this.dao.findForList(VelocityCoreUtils.render(sqlMap.get("findByObj"), paramsMap), paramsMap);
    }

    public List<Map<String, Object>> findListMapByObj(Map<String, Object> paramsMap) {
        Map<String, String> sqlMap = OverallSituationStatic.getTableAllSql(DfieldCheck.class.getSimpleName());
        return this.dao.findListMap(VelocityCoreUtils.render(sqlMap.get("findListMapByObj"), paramsMap), paramsMap);
    }

    public List<DfieldCheck> findByPageObj(Map<String, Object> paramsMap, PageBean page) {
        Map<String, String> sqlMap = OverallSituationStatic.getTableAllSql(DfieldCheck.class.getSimpleName());
        if (page == null) {
            page = new PageBean();
        }
        paramsMap.put("pageSize", page.getPageSize());
        paramsMap.put("startNum", page.getStartNum());
        return this.dao.findForList(VelocityCoreUtils.render(sqlMap.get("findByPageObj"), paramsMap), paramsMap);
    }

    public List<Map<String, Object>> findListMapByPageObj(Map<String, Object> paramsMap, PageBean page) {
        Map<String, String> sqlMap = OverallSituationStatic.getTableAllSql(DfieldCheck.class.getSimpleName());
        if (page == null) {
            page = new PageBean();
        }
        paramsMap.put("pageSize", page.getPageSize());
        paramsMap.put("startNum", page.getStartNum());
        return this.dao.findListMap(VelocityCoreUtils.render(sqlMap.get("findListMapByPageObj"), paramsMap), paramsMap);

    }

    public Map<String, Object> count(Map<String, Object> paramsMap) {
        Map<String, String> sqlMap = OverallSituationStatic.getTableAllSql(DfieldCheck.class.getSimpleName());
        return this.dao.findOneByMap(VelocityCoreUtils.render(sqlMap.get("count"), paramsMap), paramsMap);
    }
}