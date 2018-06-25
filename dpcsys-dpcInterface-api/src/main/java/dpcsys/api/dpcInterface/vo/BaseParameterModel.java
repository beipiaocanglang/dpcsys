package dpcsys.api.dpcInterface.vo;

import java.io.Serializable;

import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 共通参数传递
 *
 * @author : lijianjun
 * @version V1.0
 * @Description: TODO
 * @Company:casst
 * @date: 2017年3月30日 下午4:54:20
 */
@SuppressWarnings("serial")
public class BaseParameterModel implements Serializable {

    // 当前登录用户ID
    @ApiModelProperty(value = "当前登录用户ID", required = true)
    private Long curUserId;
    // 当前登录用户名
    @ApiModelProperty(value = "当前登录用户名", required = true)
    private String curLoginName;
    // 当前登录企业ID
    @ApiModelProperty(value = "当前登录企业ID", required = true)
    private String curCompanyId;

    public String getCurCompanyId() {
        return curCompanyId;
    }

    public void setCurCompanyId(String curCompanyId) {
        this.curCompanyId = curCompanyId;
    }

    public String getCurLoginName() {
        return curLoginName;
    }

    public void setCurLoginName(String curLoginName) {
        this.curLoginName = curLoginName;
    }

    public Long getCurUserId() {
        return curUserId;
    }

    public void setCurUserId(Long curUserId) {
        this.curUserId = curUserId;
    }
}