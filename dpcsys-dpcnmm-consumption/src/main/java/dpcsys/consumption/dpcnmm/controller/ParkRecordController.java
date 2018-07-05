package dpcsys.consumption.dpcnmm.controller;

import dpcsys.api.dpcnmm.model.ParkingConfig;
import dpcsys.api.dpcnmm.model.ParkingRecord;
import dpcsys.api.dpcnmm.model.constant.CardTypeEnum;
import dpcsys.api.dpcnmm.model.constant.ParkTypeEnum;
import dpcsys.api.dpcnmm.model.tempPojo.SysUser;
import dpcsys.api.dpcnmm.service.IParkConfigService;
import dpcsys.api.dpcnmm.service.IParkRecordService;
import dpcsys.consumption.frame.util.DateConvertUtils;
import dpcsys.consumption.frame.util.HttpClientUtils;
import dpcsys.consumption.frame.util.JsonUtil;
import dpcsys.consumption.frame.util.ResultDTOBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 停车记录表的控制层
 * author : sunpanhu
 * create time : 2018-5-25 下午4:08
 */
@Controller
@RequestMapping("/parkRecord/")
public class ParkRecordController {
    public static String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";
    @Resource
    private IParkRecordService parkRecordService;

    @Resource
    private IParkConfigService parkConfigService;

    @Value("${HTTP_CLIENT_USER_URL}")
    private String HTTP_CLIENT_USER_URL;

    /**
     * 对方调用此接口传入 车牌号，进场时间等信息
     * 保存数据到  ParkRecord 表
     * /parkRecord/insertParkRecordData
     * author : sunpanhu
     * createTime : 2018-5-25 下午4:02
     * @return
     */
    @ResponseBody
    @RequestMapping("insertParkRecordData")
    public Object insertSelective(ParkingRecord parkingRecord){

        //调用接口1：接口调用获取车牌号
        if (StringUtils.isBlank(parkingRecord.getDriveIntoTimeStr())) {
            return ResultDTOBuilder.failure("008", "有效参数【驶入时间】不能为空");
        }
        if (StringUtils.isBlank(parkingRecord.getPlateNumber())) {
            return ResultDTOBuilder.failure("008", "有效参数【车牌号】不能为空");
        }

        //调用接口2：车牌号 ——> 调小程序接口查会员ID
        Map<String, Object> params = new LinkedHashMap<String, Object>();
        params.put("plateNumber",parkingRecord.getPlateNumber());
        String url = HTTP_CLIENT_USER_URL + "findUserByPlateNumber";
        String userId = "";
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            String resultHttpStr = HttpClientUtils.doGet(client, url, params);
            SysUser user = JsonUtil.parseJson(resultHttpStr, SysUser.class);
            userId = String.valueOf(user.getId());
        } catch (Exception e) {
            e.getMessage();
            return ResultDTOBuilder.failure("008", "获取用户信息异常");
        }

        //调用接口3：会员ID ——> 调接口卡类别
        String storeId = parkingRecord.getStoreId();//模拟门店编号
        Integer cardType = null;
        for (CardTypeEnum cartTypeEnum : CardTypeEnum.values()){
            if (cartTypeEnum.getCode() == 0) {
                cardType = cartTypeEnum.getCode();
                break;
            }
        }

        Date driveIntoDate = DateConvertUtils.stringToDate(parkingRecord.getDriveIntoTimeStr(), DATEFORMAT);

        //调用接口4：根据门店编号 和 卡类别 查询当前类型的卡 在 当前门店 享受多长时间的免费停车
        ParkingConfig parkingConfig = new ParkingConfig();
        parkingConfig.setStoreId(storeId);
        parkingConfig.setCardType(cardType);
        parkingConfig.setValidStartTime(driveIntoDate);

        List<ParkingConfig> parkingConfigList = parkConfigService.selectByRequire(parkingConfig);

        String cId = "";
        if (parkingConfigList != null && parkingConfigList.size() > 0){
            if (parkingConfigList.size() == 2) {
                for (int x=0; x<parkingConfigList.size(); x++ ) {
                    String id = String.valueOf(parkingConfigList.get(x).getId());
                    if (x == parkingConfigList.size() - 1) {
                        cId += id;
                    } else {
                        cId += id + ",";
                    }
                }
            } else {
                cId = String.valueOf(parkingConfigList.get(0).getId());
            }
        }

        parkingRecord.setDriveIntoTime(driveIntoDate);//驶入时间
        parkingRecord.setStoreId(storeId);
        parkingRecord.setcId(cId);//配置表id
        parkingRecord.setmId(userId);
        parkingRecord.setCreateTime(new Date());
        parkingRecord.setUpdateTime(new Date());

        List<ParkingRecord> parkingRecords = parkRecordService.selectByRequire(parkingRecord);
        if (parkingRecords != null && parkingRecords.size() > 0) {
            return ResultDTOBuilder.failure("008", "当前车辆有未结算记录");
        }

        //保存停车记录信息
        int row = parkRecordService.insertSelective(parkingRecord);
        if (row != 1) {
            return ResultDTOBuilder.failure("008", "保存当前进场车辆信息失败，请稍候重试");
        }

        ParkingRecord parkingRecordResult = parkRecordService.selectByPrimaryKey(parkingRecord.getId());

        return ResultDTOBuilder.success(parkingRecordResult);
    }

    /**
     * 驶出时调用
     * 修改  parkRecord 表数据
     * /parkRecord/updateRecordData
     * author : sunpanhu
     * createTime : 2018-5-25 下午4:02
     * @param parkingRecord
     * @return
     */
    @ResponseBody
    @RequestMapping("updateRecordData")
    public Object updateRecordData(ParkingRecord parkingRecord){
        //一条记录的id
        Long id = parkingRecord.getId();
        if(id == null) {
            return ResultDTOBuilder.failure("003", "记录标识不能为空");
        }
        //车牌号
        if (StringUtils.isBlank(parkingRecord.getPlateNumber())) {
            return ResultDTOBuilder.failure("003", "车牌号不能为空");
        }
        //驶出时间
        if (StringUtils.isBlank(parkingRecord.getOutOfTimeStr())) {
            return ResultDTOBuilder.failure("003", "驶出时间不能为空");
        }

        //根据记录id查询当前要驶出的车的停车记录
        ParkingRecord parkingRecordResult = parkRecordService.selectByPrimaryKey(id);
        if (parkingRecordResult == null) {
            return ResultDTOBuilder.failure("003", "未查到驶入记录");
        }

        //获取驶入时间
        Date driveIntoTime = parkingRecordResult.getDriveIntoTime();
        //格式化和驶入时间一样的驶出时间
        Date outOfTime = DateConvertUtils.stringToDate(parkingRecord.getOutOfTimeStr(), DATEFORMAT);

        if (outOfTime.before(driveIntoTime)) {
            return ResultDTOBuilder.failure("003", "驶出时间不能小于驶入时间");
        }

        //计算停留的毫秒值
        long residenceTime = outOfTime.getTime() - driveIntoTime.getTime();
        //总停车时间(单位：小时),不足一小时的按一小时算
        long dateDiff = DateConvertUtils.dateDiff(residenceTime);

        String cId = parkingRecordResult.getcId();
        if (StringUtils.isBlank(cId)) {
            //TODO 为空时重新查询数据
        }

        String[] ids = cId.split(",");
        List<ParkingConfig> parkingConfigList = parkConfigService.selectByMultiKey(ids);
        ParkingConfig configData = getParkingConfig(parkingConfigList);

        //获取总的免费停车时间
        long totalTimeHours = configData == null ? 0L : configData.getTotalTime();

        //驶出时间
        parkingRecord.setOutOfTime(outOfTime);
        //总停留时长
        parkingRecord.setDurationTime(dateDiff);
        //配置表id，多个之间用「,」隔开
        parkingRecord.setcId(cId);
        //当前用户当天总免费停车时间
        parkingRecord.setTotalTime(totalTimeHours);

        int row = parkRecordService.updateByPrimaryKeySelective(parkingRecord);
        if (row != 1) {
            return ResultDTOBuilder.failure("003", "驶出信息保存失败，请稍候重试");
        }

        //根据记录id查询当前要驶出的车的停车记录
        ParkingRecord parkingData = parkRecordService.selectByPrimaryKey(id);

        return ResultDTOBuilder.success(parkingData);
    }

    public ParkingConfig getParkingConfig(List<ParkingConfig> parkingConfigList){
        ParkingConfig configData = null;
        if (parkingConfigList != null && parkingConfigList.size() > 0){
            if (parkingConfigList.size() == 2) {
                Integer selectScheme = 0;
                for (int x = 0; x < parkingConfigList.size(); x++) {
                    if (parkingConfigList.get(x).getParkType() == 1) {
                        selectScheme = parkingConfigList.get(x).getSelectScheme();
                    }
                }

                switch (selectScheme) {
                    case 0:
                        for (ParkingConfig config : parkingConfigList) {
                            if (config.getParkType() == 0) {
                                configData = config;
                            }
                        }
                        break;
                    case 1:
                        for (ParkingConfig config : parkingConfigList) {
                            if (config.getParkType() == 1) {
                                configData = config;
                            }
                        }
                        break;
                    case 2:
                        Long totalTime = 0L;
                        for (ParkingConfig config : parkingConfigList) {
                            totalTime += config.getTotalTime();
                            if (config.getParkType() == 0) {
                                configData = config;
                                configData.setParkType(2);
                                configData.setParkTypeDesc(ParkTypeEnum.getByCode(2).getDesc());
                            }
                        }
                        configData.setTotalTime(totalTime);
                        break;
                }
            } else {
                configData = parkingConfigList.get(0);
            }
        }
        return configData;
    }
}
