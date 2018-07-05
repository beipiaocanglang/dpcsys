package dpcsys.api.dpcnmm.model;

import dpcsys.api.dpcnmm.model.constant.EnableFlagEnum;
import dpcsys.api.dpcnmm.model.constant.ParkTypeEnum;
import dpcsys.api.dpcnmm.model.constant.SchemeEnum;

import java.util.Date;

/**
 * 停车配置 pojo
 * author : sunpanhu
 * createTime : 2018-5-25 上午9:42
 */
public class ParkingConfig extends BaseBo{

    //门店编号
    private String storeId;

    //会员卡类型(0：白金卡、1：金卡、2：银卡、3：VIP卡、4：至尊卡、5：金尊卡、6：贵宾卡)
    private Integer cardType;
    private String cardTypeDesc;

    //停车类型(0：长期停车、1：短期停车)
    private Integer parkType;
    private String parkTypeDesc;

    //免费停车总时长
    private Long totalTime;

    //有效开始时间
    private Date validStartTime;
    private String validStartTimeStr;

    //有效结束时间
    private Date validEndTime;
    private String validEndTimeStr;

    //当长短期方案共存时需要选择的方案 (0：使用长期方案、1：使用短期方案、2：合并时间)
    private Integer selectScheme;
    private String selectSchemeDesc;

    //状态节点 (0：过期、1：正常)
    private Integer statusFlag;
    private String statusFlagDesc;


    public String getStoreId() {
        return storeId;
    }
    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public Integer getCardType() {
        return cardType;
    }
    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public Integer getParkType() {
        return parkType;
    }
    public void setParkType(Integer parkType) {
        this.parkType = parkType;
    }

    public Long getTotalTime() {
        return totalTime;
    }
    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    public Date getValidStartTime() {
        return validStartTime;
    }
    public void setValidStartTime(Date validStartTime) {
        this.validStartTime = validStartTime;
    }

    public Date getValidEndTime() {
        return validEndTime;
    }
    public void setValidEndTime(Date validEndTime) {
        this.validEndTime = validEndTime;
    }

    public Integer getStatusFlag() {
        return statusFlag;
    }
    public void setStatusFlag(Integer statusFlag) {
        this.statusFlag = statusFlag;
    }

    public String getValidStartTimeStr() {
        return validStartTimeStr;
    }
    public void setValidStartTimeStr(String validStartTimeStr) {
        this.validStartTimeStr = validStartTimeStr;
    }

    public String getValidEndTimeStr() {
        return validEndTimeStr;
    }
    public void setValidEndTimeStr(String validEndTimeStr) {
        this.validEndTimeStr = validEndTimeStr;
    }

    public String getCardTypeDesc() {
        return cardTypeDesc;
    }
    public void setCardTypeDesc(String cardTypeDesc) {
        this.cardTypeDesc = cardTypeDesc;
    }

    public String getParkTypeDesc() {
        if (parkType != null) {
            ParkTypeEnum parkTypeEnum = ParkTypeEnum.getByCode(parkType);
            return parkTypeEnum == null? "" : parkTypeEnum.getDesc();
        }
        return "";
    }
    public void setParkTypeDesc(String parkTypeDesc) {
        this.parkTypeDesc = parkTypeDesc;
    }

    public Integer getSelectScheme() {
        return selectScheme;
    }
    public void setSelectScheme(Integer selectScheme) {
        this.selectScheme = selectScheme;
    }

    public String getSelectSchemeDesc() {
        if (selectScheme != null) {
            SchemeEnum schemeEnum = SchemeEnum.getByCode(selectScheme);
            return schemeEnum == null? "" : schemeEnum.getDesc();
        }
        return selectSchemeDesc;
    }
    public void setSelectSchemeDesc(String selectSchemeDesc) {
        this.selectSchemeDesc = selectSchemeDesc;
    }

    public String getStatusFlagDesc() {
        if (statusFlag != null) {
            EnableFlagEnum enableFlagEnum = EnableFlagEnum.getByCode(statusFlag);
            return enableFlagEnum == null? "" : enableFlagEnum.getDesc();
        }
        return "";
    }
    public void setStatusFlagDesc(String statusFlagDesc) {
        this.statusFlagDesc = statusFlagDesc;
    }
}