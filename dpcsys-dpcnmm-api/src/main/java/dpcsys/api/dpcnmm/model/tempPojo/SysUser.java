package dpcsys.api.dpcnmm.model.tempPojo;

import java.io.Serializable;
import java.util.Date;

/**
 * author : sunpanhu
 * create time : 2018-5-28 上午10:37
 */
public class SysUser implements Serializable{
    // id
    private Long id;
    //用户名
    private String uname;
    //密码
    private String pswd;
    //真实姓名
    private String rname;
    //手机
    private String tel;
    //头像
    private String headUrl;
    //邮箱
    private String email;
    //万达用户平台ID
    private String appId;
    //万达用户code
    private String userCode;
    //职位
    private String jobName;
    //机构ID
    private String orgId;
    //机构名称
    private String orgName;
    //状态（0未激活 1 激活 2 冻结）
    private Integer status;
    // 创建时间
    private Date creatTm;
    // 更新时间
    private Date updtTm;
    // 0:不可用 1:可用
    private Integer enabFlag;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatTm() {
        return creatTm;
    }

    public void setCreatTm(Date creatTm) {
        this.creatTm = creatTm;
    }

    public Date getUpdtTm() {
        return updtTm;
    }

    public void setUpdtTm(Date updtTm) {
        this.updtTm = updtTm;
    }

    public Integer getEnabFlag() {
        return enabFlag;
    }

    public void setEnabFlag(Integer enabFlag) {
        this.enabFlag = enabFlag;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "id=" + id +
                ", uname='" + uname + '\'' +
                ", pswd='" + pswd + '\'' +
                ", rname='" + rname + '\'' +
                ", tel='" + tel + '\'' +
                ", headUrl='" + headUrl + '\'' +
                ", email='" + email + '\'' +
                ", appId='" + appId + '\'' +
                ", userCode='" + userCode + '\'' +
                ", jobName='" + jobName + '\'' +
                ", orgId='" + orgId + '\'' +
                ", orgName='" + orgName + '\'' +
                ", status=" + status +
                ", creatTm=" + creatTm +
                ", updtTm=" + updtTm +
                ", enabFlag=" + enabFlag +
                '}';
    }
}
