package dpcsys.api.dpcnmm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * author : sunpanhu
 * create time : 2018-5-31 下午1:27
 */
public class BaseBo implements Serializable{

    public static String DATEFORMAT = "yyyy-MM-dd";

    //唯一标识
    private Long id;
    //创建时间
    private Date createTime;
    //修改时间
    private Date updateTime;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
