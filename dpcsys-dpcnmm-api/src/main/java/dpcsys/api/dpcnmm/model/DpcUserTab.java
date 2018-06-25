package dpcsys.api.dpcnmm.model;

import org.apache.commons.lang.builder.*;
import faner.dplatformSpringjdbc.api.frame.model.BaseModel;

/**
 * @author : lijianjun
 * @version V2.0
 * @Company: faner
 */
@SuppressWarnings("serial")
public class DpcUserTab extends BaseModel {

    // 字段
    //columns START
    private java.lang.Long id;
    private java.lang.String userId;
    private java.lang.String password;
    private java.lang.String userName;
    private java.lang.String realName;
    private java.lang.String leadersTel;
    private java.lang.String mailAddress;
    private java.lang.String address;
    private java.lang.String userType;
    private java.lang.String remarks;
    private java.lang.String controlConfirmFlg;
    private java.lang.String companyId;
    private java.lang.String roleId;
    private java.lang.String delFlg;
    private java.lang.String insUser;
    private java.util.Date insTime;
    private java.lang.String updUser;
    private java.util.Date updTime;
    private java.lang.String reserveCol1;
    private java.lang.String reserveCol2;
    private java.lang.String reserveCol3;
    private java.lang.String reserveCol4;
    private java.lang.String reserveCol5;
    //columns END

    // 属性get,set
    public void setId(java.lang.Long value) {
        this.id = value;
    }

    public java.lang.Long getId() {
        return this.id;
    }

    public void setUserId(java.lang.String value) {
        this.userId = value;
    }

    public java.lang.String getUserId() {
        return this.userId;
    }

    public void setPassword(java.lang.String value) {
        this.password = value;
    }

    public java.lang.String getPassword() {
        return this.password;
    }

    public void setUserName(java.lang.String value) {
        this.userName = value;
    }

    public java.lang.String getUserName() {
        return this.userName;
    }

    public void setRealName(java.lang.String value) {
        this.realName = value;
    }

    public java.lang.String getRealName() {
        return this.realName;
    }

    public void setLeadersTel(java.lang.String value) {
        this.leadersTel = value;
    }

    public java.lang.String getLeadersTel() {
        return this.leadersTel;
    }

    public void setMailAddress(java.lang.String value) {
        this.mailAddress = value;
    }

    public java.lang.String getMailAddress() {
        return this.mailAddress;
    }

    public void setAddress(java.lang.String value) {
        this.address = value;
    }

    public java.lang.String getAddress() {
        return this.address;
    }

    public void setUserType(java.lang.String value) {
        this.userType = value;
    }

    public java.lang.String getUserType() {
        return this.userType;
    }

    public void setRemarks(java.lang.String value) {
        this.remarks = value;
    }

    public java.lang.String getRemarks() {
        return this.remarks;
    }

    public void setControlConfirmFlg(java.lang.String value) {
        this.controlConfirmFlg = value;
    }

    public java.lang.String getControlConfirmFlg() {
        return this.controlConfirmFlg;
    }

    public void setCompanyId(java.lang.String value) {
        this.companyId = value;
    }

    public java.lang.String getCompanyId() {
        return this.companyId;
    }

    public void setRoleId(java.lang.String value) {
        this.roleId = value;
    }

    public java.lang.String getRoleId() {
        return this.roleId;
    }

    public void setDelFlg(java.lang.String value) {
        this.delFlg = value;
    }

    public java.lang.String getDelFlg() {
        return this.delFlg;
    }

    public void setInsUser(java.lang.String value) {
        this.insUser = value;
    }

    public java.lang.String getInsUser() {
        return this.insUser;
    }

    public void setInsTime(java.util.Date value) {
        this.insTime = value;
    }

    public java.util.Date getInsTime() {
        return this.insTime;
    }

    public void setUpdUser(java.lang.String value) {
        this.updUser = value;
    }

    public java.lang.String getUpdUser() {
        return this.updUser;
    }

    public void setUpdTime(java.util.Date value) {
        this.updTime = value;
    }

    public java.util.Date getUpdTime() {
        return this.updTime;
    }

    public void setReserveCol1(java.lang.String value) {
        this.reserveCol1 = value;
    }

    public java.lang.String getReserveCol1() {
        return this.reserveCol1;
    }

    public void setReserveCol2(java.lang.String value) {
        this.reserveCol2 = value;
    }

    public java.lang.String getReserveCol2() {
        return this.reserveCol2;
    }

    public void setReserveCol3(java.lang.String value) {
        this.reserveCol3 = value;
    }

    public java.lang.String getReserveCol3() {
        return this.reserveCol3;
    }

    public void setReserveCol4(java.lang.String value) {
        this.reserveCol4 = value;
    }

    public java.lang.String getReserveCol4() {
        return this.reserveCol4;
    }

    public void setReserveCol5(java.lang.String value) {
        this.reserveCol5 = value;
    }

    public java.lang.String getReserveCol5() {
        return this.reserveCol5;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("Id", getId())
                .append("UserId", getUserId())
                .append("Password", getPassword())
                .append("UserName", getUserName())
                .append("RealName", getRealName())
                .append("LeadersTel", getLeadersTel())
                .append("MailAddress", getMailAddress())
                .append("Address", getAddress())
                .append("UserType", getUserType())
                .append("Remarks", getRemarks())
                .append("ControlConfirmFlg", getControlConfirmFlg())
                .append("CompanyId", getCompanyId())
                .append("RoleId", getRoleId())
                .append("DelFlg", getDelFlg())
                .append("InsUser", getInsUser())
                .append("InsTime", getInsTime())
                .append("UpdUser", getUpdUser())
                .append("UpdTime", getUpdTime())
                .append("ReserveCol1", getReserveCol1())
                .append("ReserveCol2", getReserveCol2())
                .append("ReserveCol3", getReserveCol3())
                .append("ReserveCol4", getReserveCol4())
                .append("ReserveCol5", getReserveCol5())
                .toString();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(getId())
                .toHashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof DpcUserTab == false) return false;
        if (this == obj) return true;
        DpcUserTab other = (DpcUserTab) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }
}

