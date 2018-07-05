package dpcsys.consumption.dpcnmm.controller;
import dpcsys.api.dpcnmm.model.ParkingConfig;
import dpcsys.api.dpcnmm.model.constant.CardTypeEnum;
import dpcsys.api.dpcnmm.model.constant.EnableFlagEnum;
import dpcsys.api.dpcnmm.service.IParkConfigService;
import dpcsys.consumption.frame.util.DateConvertUtils;
import dpcsys.consumption.frame.util.ResultDTOBuilder;
import faner.dplatformSpringjdbc.api.frame.util.tools.json.JsonCoreUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 停车配置表的控制层
 * author: canglang
 * create time: 2018/5/24 22:51
 */
@Controller
@RequestMapping("/parkConfig/")
@SuppressWarnings({"all", "rawtypes"})
public class ParkConfigConteoller {

    @Resource
    private IParkConfigService parkConfigService;

    @RequestMapping("index")
    public String test(){
        return "index";
    }

    /**
     * 添加卡 保存数据到  ParkConfigData 表
     * /parkConfig/insertParkConfigData
     * author : sunpanhu
     * createTime : 2018-5-25 下午4:02
     * @param parkingConfig
     * @return
     */
    @ResponseBody
    @PostMapping("insertParkConfigData")
    public Object insertSelective(@RequestBody ParkingConfig parkingConfig){

        Date startDate = DateConvertUtils.stringToDate(parkingConfig.getValidStartTimeStr(), parkingConfig.DATEFORMAT);
        Date endDate = DateConvertUtils.stringToDate(parkingConfig.getValidEndTimeStr(), parkingConfig.DATEFORMAT);

        if (endDate.before(startDate)) {
            return ResultDTOBuilder.failure("006", "结束时间必须大于或等于开始时间");
        }
        parkingConfig.setValidStartTime(startDate);
        parkingConfig.setValidEndTime(endDate);

        if (endDate.before(new Date())) {
            return ResultDTOBuilder.failure("006", "结束时间应大于当前时间");
        }

        List<ParkingConfig> activeTimeIsExist = parkConfigService.findActiveTimeIsExist(parkingConfig);
        if (activeTimeIsExist != null && activeTimeIsExist.size() > 0){
            return ResultDTOBuilder.failure("006", "已存在包含当前时间策略的活动");
        }

        int row = parkConfigService.insertSelective(parkingConfig);
        if (row != 1){
            return ResultDTOBuilder.failure("006", "添加失败，请稍候再试");
        }

        ParkingConfig parkingConfigResult = parkConfigService.selectByPrimaryKey(parkingConfig.getId());

        return ResultDTOBuilder.success(parkingConfigResult);
    }

    /**
     * 查询所有
     * /parkConfig/findList
     * @return
     */
    @ResponseBody
    @GetMapping("findList")
    public Object findList(@RequestParam String storeId){

        //模拟查询前通过门店id 调用外部接口获取当前门店的所有卡类型
        for (CardTypeEnum cardTypeEnum : CardTypeEnum.values()){
            Integer code = cardTypeEnum.getCode();
            String desc = cardTypeEnum.getDesc();

            //根据门店ID和通过接口查到的卡类型code查询数据库 没有就插入
            ParkingConfig parkingConfig = new ParkingConfig();
            parkingConfig.setCardType(code);
            parkingConfig.setStoreId(storeId);

            List<ParkingConfig> parkConfigResult = parkConfigService.selectByParkConfigData(parkingConfig);

            if (parkConfigResult == null || parkConfigResult.size() < 1) {
                parkingConfig.setCardTypeDesc(desc);
                int row = parkConfigService.insertSelective(parkingConfig);
            }
        }

        //查询配置表中的所有数据
        List<ParkingConfig> parkingConfigList = parkConfigService.findList(storeId);

        //拼接时间 为页面使用
        if (parkingConfigList != null && parkingConfigList.size() > 0){
            for (ParkingConfig parkingConfig : parkingConfigList) {
                Date validStartTime = parkingConfig.getValidStartTime();
                Date validEndTime = parkingConfig.getValidEndTime();

                if (validStartTime != null && validEndTime != null){
                    String startTime = DateConvertUtils.dateToString(validStartTime, parkingConfig.DATEFORMAT);
                    String endTime = DateConvertUtils.dateToString(validEndTime, parkingConfig.DATEFORMAT);
                    parkingConfig.setValidStartTimeStr(startTime + " ~ " + endTime);

                    if (validEndTime.before(new Date()) && parkingConfig.getStatusFlag() == 1) {
                        ParkingConfig config = new ParkingConfig();
                        config.setId(parkingConfig.getId());
                        config.setStatusFlag(EnableFlagEnum.EXPIRE.getCode());

                        //如果有活动结束的 修改数据库 当前记录的活动状态
                        parkConfigService.updateByPrimaryKeySelective(config);

                        //修改当前已经查出来的数据
                        parkingConfig.setStatusFlag(0);
                        parkingConfig.setStatusFlagDesc(EnableFlagEnum.EXPIRE.getDesc());
                    }
                }
            }
        }
        return ResultDTOBuilder.success(parkingConfigList);
    }

    /**
     * 获取所有卡类别
     * @param storeId
     * @return
     */
    @ResponseBody
    @GetMapping("findCardType")
    public Object findCardType(@RequestParam String storeId){
        List<ParkingConfig> cardTypeMap = parkConfigService.findCardType(storeId);
        return ResultDTOBuilder.success(cardTypeMap);
    }

    /**
     * 根据停车配置的活动记录id删除当前记录数
     * /parkConfig/deleteActiveRecord
     * author : sunpanhu
     * createTime : 2018-6-1 上午10:08
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping("deleteActiveRecord")
    public Object deleteActiveRecord(@RequestParam Long id){
        try {
            ParkingConfig parkingConfig = new ParkingConfig();
            parkingConfig.setId(id);
            parkingConfig.setStatusFlag(EnableFlagEnum.EXPIRE.getCode());
            //TODO 删除时可能需要修改记录表中对应的id为null
            int row = parkConfigService.updateByPrimaryKeySelective(parkingConfig);
            return ResultDTOBuilder.success(row);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultDTOBuilder.failure("007", "删除失败");
        }
    }
}
