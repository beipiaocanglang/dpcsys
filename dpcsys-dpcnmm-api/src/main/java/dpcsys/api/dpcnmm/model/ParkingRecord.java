package dpcsys.api.dpcnmm.model;

import java.util.Date;

/**
 * 停车记录 pojo
 * author : sunpanhu
 * createTime : 2018-5-25 上午9:41
 */
public class ParkingRecord extends BaseBo{
    //对应配置表id
    private String cId;
    //车牌号
    private String plateNumber;
    //门店id
    private String storeId;
    //会员id
    private String mId;
    //驶入时间
    private Date driveIntoTime;
    private String driveIntoTimeStr;
    //驶出时间
    private Date outOfTime;
    private String outOfTimeStr;
    //停车时间(从进到出共停留了多长时间)
    private Long durationTime;
    //免费停车总时长
    private Long totalTime;


    public String getcId() {
        return cId;
    }
    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }
    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getStoreId() {
        return storeId;
    }
    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getmId() {
        return mId;
    }
    public void setmId(String mId) {
        this.mId = mId;
    }

    public Date getDriveIntoTime() {
        return driveIntoTime;
    }
    public void setDriveIntoTime(Date driveIntoTime) {
        this.driveIntoTime = driveIntoTime;
    }

    public String getDriveIntoTimeStr() {
        return driveIntoTimeStr;
    }
    public void setDriveIntoTimeStr(String driveIntoTimeStr) {
        this.driveIntoTimeStr = driveIntoTimeStr;
    }

    public Date getOutOfTime() {
        return outOfTime;
    }
    public void setOutOfTime(Date outOfTime) {
        this.outOfTime = outOfTime;
    }

    public String getOutOfTimeStr() {
        return outOfTimeStr;
    }
    public void setOutOfTimeStr(String outOfTimeStr) {
        this.outOfTimeStr = outOfTimeStr;
    }

    public Long getDurationTime() {
        return durationTime;
    }
    public void setDurationTime(Long durationTime) {
        this.durationTime = durationTime;
    }

    public Long getTotalTime() {
        return totalTime;
    }
    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }
}