package dpcsys.consumption.dpcInterface.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import dpcsys.api.dpcInterface.constants.DpcInterfaceResponseConstant;
import dpcsys.api.dpcInterface.constants.DpcInterfaceResponseHashMap;
import dpcsys.api.dpcInterface.model.DfieldCheck;
import dpcsys.api.dpcInterface.service.DfieldCheckService;
import dpcsys.api.dpcInterface.vo.BaseParameterModel;
import dpcsys.api.dpcInterface.vo.LogParameterModel;
import dpcsys.consumption.frame.web.controller.BaseController;
import faner.dplatformSpringjdbc.api.frame.util.tools.json.JsonCoreUtil;
import faner.dplatformSpringjdbc.api.frame.util.tools.object.ListCoreUtil;
import faner.dplatformSpringjdbc.api.frame.util.tools.object.MyBeanCoreUtils;
import faner.dplatformSpringjdbc.api.frame.util.tools.object.ObjectCoreUtil;
import faner.dplatformSpringjdbc.api.frame.util.tools.page.PageBean;

/**
 * @version V2.0
 * @Company:faner
 * @author: lijianjun
 */
@Controller
@Scope("prototype")
@RequestMapping("/dfieldCheck")
public class DfieldCheckController extends BaseController {

    @Autowired(required = false)
    private DfieldCheckService dfieldCheckService;

    /**
     * 新增
     *
     * @param dfieldCheck 新增对象
     * @method: saveDfieldCheck
     * @Description: 新增
     * @author : lijianjun
     */
    @ApiOperation(value = "新增", notes = "新增对象", response = DpcInterfaceResponseHashMap.class)
    @RequestMapping(value = "/saveDfieldCheck", method = RequestMethod.POST)
    public void saveDfieldCheck(@ModelAttribute BaseParameterModel baseModel, @ModelAttribute LogParameterModel logModel, @ModelAttribute DfieldCheck dfieldCheck) {
        DpcInterfaceResponseHashMap map = new DpcInterfaceResponseHashMap();
        dfieldCheck.setId(null);
        dfieldCheck.setDelFlg("0");
        dfieldCheck.setInsUser(baseModel.getCurLoginName());
        dfieldCheck.setInsTime(new Date());
        dfieldCheck.setUpdUser(baseModel.getCurLoginName());
        dfieldCheck.setUpdTime(new Date());
        int id = dfieldCheckService.save(dfieldCheck);
        if (id > 0) {
            dfieldCheck.setId(java.lang.Long.valueOf(id));
            map.setData(dfieldCheck);
        } else {
            map.setCode(DpcInterfaceResponseConstant.CODE_NULL);
            map.setMsg(DpcInterfaceResponseConstant.MSG_NULL);
        }
        JsonCoreUtil.outPutJson(this.getResponse(), JsonCoreUtil.toJSONString(map));
    }

    /**
     * 删除
     *
     * @param ids 删除key
     * @method: delDfieldCheckByIds
     * @Description: 删除
     * @author : lijianjun
     */
    @ApiOperation(value = "删除", notes = "删除", response = DpcInterfaceResponseHashMap.class)
    @RequestMapping(value = "/delDfieldCheckByIds", method = RequestMethod.POST)
    public void delDfieldCheckByIds(@ModelAttribute BaseParameterModel baseModel, @ModelAttribute LogParameterModel logModel, @ApiParam(value = "删除数据ids集合,使用逗号分割", required = true) @RequestParam String ids) throws Exception {
        DpcInterfaceResponseHashMap map = new DpcInterfaceResponseHashMap();
        String[] idsArr = ids.split(",");
        boolean boo = dfieldCheckService.batchDelete(idsArr);
        if (!boo) {
            map.setCode(DpcInterfaceResponseConstant.CODE_NULL);
            map.setMsg(DpcInterfaceResponseConstant.MSG_NULL);
        }
        JsonCoreUtil.outPutJson(this.getResponse(), JsonCoreUtil.toJSONString(map));
    }

    /**
     * 更新
     *
     * @param dfieldCheck 更新对象
     * @method: updDfieldCheck
     * @Description: 更新
     * @author : lijianjun
     */
    @ApiOperation(value = "更新", notes = "更新对象", response = DpcInterfaceResponseHashMap.class)
    @RequestMapping(value = "/updDfieldCheck", method = RequestMethod.POST)
    public void updDfieldCheck(@ModelAttribute BaseParameterModel baseModel, @ModelAttribute LogParameterModel logModel, @ModelAttribute DfieldCheck dfieldCheck) throws Exception {
        DpcInterfaceResponseHashMap map = new DpcInterfaceResponseHashMap();
        DfieldCheck dfieldCheckObj = dfieldCheckService.get(java.lang.Long.valueOf(dfieldCheck.getId()));
        MyBeanCoreUtils.copyNotNullProperties(dfieldCheckObj, dfieldCheck);
        dfieldCheckObj.setUpdUser(baseModel.getCurLoginName());
        dfieldCheckObj.setUpdTime(new Date());
        int num = dfieldCheckService.update(dfieldCheckObj);
        if (num > 0) {
            map.setData(dfieldCheck);
        } else {
            map.setCode(DpcInterfaceResponseConstant.CODE_NULL);
            map.setMsg(DpcInterfaceResponseConstant.MSG_NULL);
        }
        JsonCoreUtil.outPutJson(this.getResponse(), JsonCoreUtil.toJSONString(map));
    }

    /**
     * 根据ID查询
     *
     * @param dfieldCheckId id
     * @method: getDfieldCheckById
     * @Description: 根据ID查询
     * @author : lijianjun
     */
    @ApiOperation(value = "根据ID查询", notes = "根据ID查询", response = DpcInterfaceResponseHashMap.class)
    @RequestMapping(value = "/getDfieldCheckById", method = RequestMethod.POST)
    public void getDfieldCheckById(@ModelAttribute BaseParameterModel baseModel, @ModelAttribute LogParameterModel logModel, @ApiParam(value = "主键ID", required = true) @RequestParam java.lang.Long id) {
        DpcInterfaceResponseHashMap map = new DpcInterfaceResponseHashMap();
        DfieldCheck dfieldCheck = dfieldCheckService.get(java.lang.Long.valueOf(id));
        if (ObjectCoreUtil.isNotNull(dfieldCheck)) {
            map.setData(dfieldCheck);
        } else {
            map.setCode(DpcInterfaceResponseConstant.CODE_NULL);
            map.setMsg(DpcInterfaceResponseConstant.MSG_NULL);
        }
        JsonCoreUtil.outPutJson(this.getResponse(), JsonCoreUtil.toJSONString(map));
    }

    /**
     * 查询全部数据
     *
     * @method: findAllDfieldCheck
     * @Description: 查询全部数据
     * @author : lijianjun
     */
    @ApiOperation(value = "查询全部数据", notes = "查询全部数据", response = DpcInterfaceResponseHashMap.class)
    @RequestMapping(value = "/findAllDfieldCheck", method = RequestMethod.POST)
    public void findAllDfieldCheck(@ModelAttribute BaseParameterModel baseModel, @ModelAttribute LogParameterModel logModel, @ModelAttribute DfieldCheck dfieldCheck) throws Exception {
        DpcInterfaceResponseHashMap map = new DpcInterfaceResponseHashMap();
        List<DfieldCheck> dfieldCheckList = dfieldCheckService.findByObj(JsonCoreUtil.toHashMapRemoveNull(dfieldCheck));
        if (ListCoreUtil.isNotEmpty(dfieldCheckList)) {
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("dataList", dfieldCheckList);
            map.setData(dataMap);
            Map<String, Object> countMap = dfieldCheckService.count(JsonCoreUtil.toHashMapRemoveNull(dfieldCheck));
            map.setCount(countMap != null && countMap.get("count") != null ? Integer.valueOf(countMap.get("count").toString()) : 0);
        } else {
            map.setCode(DpcInterfaceResponseConstant.CODE_NULL);
            map.setMsg(DpcInterfaceResponseConstant.MSG_NULL);
        }
        JsonCoreUtil.outPutJson(this.getResponse(), JsonCoreUtil.toJSONString(map));
    }

    /**
     * 查询全部数据
     *
     * @method: findAllListMapDfieldCheck
     * @Description: 查询全部数据
     * @author : lijianjun
     */
    @ApiOperation(value = "查询全部数据", notes = "查询全部数据", response = DpcInterfaceResponseHashMap.class)
    @RequestMapping(value = "/findAllListMapDfieldCheck", method = RequestMethod.POST)
    public void findAllListMapDfieldCheck(@ModelAttribute BaseParameterModel baseModel, @ModelAttribute LogParameterModel logModel, @ModelAttribute DfieldCheck dfieldCheck) throws Exception {
        DpcInterfaceResponseHashMap map = new DpcInterfaceResponseHashMap();
        List<Map<String, Object>> dfieldCheckList = dfieldCheckService.findListMapByObj(JsonCoreUtil.toHashMapRemoveNull(dfieldCheck));
        if (ListCoreUtil.isNotEmpty(dfieldCheckList)) {
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("dataList", dfieldCheckList);
            map.setData(dataMap);
        } else {
            map.setCode(DpcInterfaceResponseConstant.CODE_NULL);
            map.setMsg(DpcInterfaceResponseConstant.MSG_NULL);
        }
        JsonCoreUtil.outPutJson(this.getResponse(), JsonCoreUtil.toJSONString(map));
    }

    /**
     * 分页查询数据
     *
     * @method: findAllDfieldCheckByPage
     * @Description: 分页查询数据
     * @author : lijianjun
     */
    @ApiOperation(value = "分页查询数据", notes = "分页查询数据", response = DpcInterfaceResponseHashMap.class)
    @RequestMapping(value = "/findAllDfieldCheckByPage", method = RequestMethod.POST)
    public void findAllDfieldCheckByPage(@ModelAttribute BaseParameterModel baseModel, @ModelAttribute LogParameterModel logModel, @ModelAttribute DfieldCheck dfieldCheck, @ModelAttribute PageBean page) throws Exception {
        DpcInterfaceResponseHashMap map = new DpcInterfaceResponseHashMap();
        List<DfieldCheck> dfieldCheckList = dfieldCheckService.findByPageObj(JsonCoreUtil.toHashMapRemoveNull(dfieldCheck), page);
        if (ListCoreUtil.isNotEmpty(dfieldCheckList)) {
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("dataList", dfieldCheckList);
            map.setData(dataMap);
            Map<String, Object> countMap = dfieldCheckService.count(JsonCoreUtil.toHashMapRemoveNull(dfieldCheck));
            map.setCount(countMap != null && countMap.get("count") != null ? Integer.valueOf(countMap.get("count").toString()) : 0);
        } else {
            map.setCode(DpcInterfaceResponseConstant.CODE_NULL);
            map.setMsg(DpcInterfaceResponseConstant.MSG_NULL);
        }
        JsonCoreUtil.outPutJson(this.getResponse(), JsonCoreUtil.toJSONString(map));
    }

    /**
     * 分页查询数据
     *
     * @method: findAllListMapDfieldCheckByPage
     * @Description: 分页查询数据
     * @author : lijianjun
     */
    @ApiOperation(value = "分页查询数据", notes = "分页查询数据", response = DpcInterfaceResponseHashMap.class)
    @RequestMapping(value = "/findAllListMapDfieldCheckByPage", method = RequestMethod.POST)
    public void findAllListMapDfieldCheckByPage(@ModelAttribute BaseParameterModel baseModel, @ModelAttribute LogParameterModel logModel, @ModelAttribute DfieldCheck dfieldCheck, @ModelAttribute PageBean page) throws Exception {
        DpcInterfaceResponseHashMap map = new DpcInterfaceResponseHashMap();
        List<Map<String, Object>> dfieldCheckList = dfieldCheckService.findListMapByPageObj(JsonCoreUtil.toHashMapRemoveNull(dfieldCheck), page);
        if (ListCoreUtil.isNotEmpty(dfieldCheckList)) {
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("dataList", dfieldCheckList);
            map.setData(dataMap);
            Map<String, Object> countMap = dfieldCheckService.count(JsonCoreUtil.toHashMapRemoveNull(dfieldCheck));
            map.setCount(countMap != null && countMap.get("count") != null ? Integer.valueOf(countMap.get("count").toString()) : 0);
        } else {
            map.setCode(DpcInterfaceResponseConstant.CODE_NULL);
            map.setMsg(DpcInterfaceResponseConstant.MSG_NULL);
        }
        JsonCoreUtil.outPutJson(this.getResponse(), JsonCoreUtil.toJSONString(map));
    }
}
