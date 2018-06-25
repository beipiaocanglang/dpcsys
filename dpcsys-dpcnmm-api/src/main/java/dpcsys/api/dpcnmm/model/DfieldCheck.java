package dpcsys.api.dpcnmm.model;

import org.apache.commons.lang.builder.*;
import faner.dplatformSpringjdbc.api.frame.model.BaseModel;

/**
 * @author : lijianjun
 * @version V2.0
 * @Company: faner
 */
@SuppressWarnings("serial")
public class DfieldCheck extends BaseModel {

    // 字段
    //columns START
    private java.lang.Long id;
    private java.lang.String name;
    private java.lang.String regular;
    private java.lang.String ownedBusiness;
    private java.lang.String remarks;
    private java.lang.String delFlg;
    private java.lang.String insUser;
    private java.util.Date insTime;
    private java.lang.String updUser;
    private java.util.Date updTime;
    //columns END

    // 属性get,set
    public void setId(java.lang.Long value) {
        this.id = value;
    }

    public java.lang.Long getId() {
        return this.id;
    }

    public void setName(java.lang.String value) {
        this.name = value;
    }

    public java.lang.String getName() {
        return this.name;
    }

    public void setRegular(java.lang.String value) {
        this.regular = value;
    }

    public java.lang.String getRegular() {
        return this.regular;
    }

    public void setOwnedBusiness(java.lang.String value) {
        this.ownedBusiness = value;
    }

    public java.lang.String getOwnedBusiness() {
        return this.ownedBusiness;
    }

    public void setRemarks(java.lang.String value) {
        this.remarks = value;
    }

    public java.lang.String getRemarks() {
        return this.remarks;
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

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("Id", getId())
                .append("Name", getName())
                .append("Regular", getRegular())
                .append("OwnedBusiness", getOwnedBusiness())
                .append("Remarks", getRemarks())
                .append("DelFlg", getDelFlg())
                .append("InsUser", getInsUser())
                .append("InsTime", getInsTime())
                .append("UpdUser", getUpdUser())
                .append("UpdTime", getUpdTime())
                .toString();
    }

    public int hashCode() {
        return new HashCodeBuilder()
                .append(getId())
                .toHashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof DfieldCheck == false) return false;
        if (this == obj) return true;
        DfieldCheck other = (DfieldCheck) obj;
        return new EqualsBuilder()
                .append(getId(), other.getId())
                .isEquals();
    }
}

