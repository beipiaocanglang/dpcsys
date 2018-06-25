package dpcsys.api.dpcInterface.vo;

import java.io.Serializable;

import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 日志基础属性
 * @Description: TODO
 * @Company:faner
 * @author : lijianjun
 * @date: 2018年4月8日 下午1:30:42
 * @version V1.0
 */
@SuppressWarnings("serial")
public class LogParameterModel implements Serializable{
	
	// 前端传：登陆;配置;监控;告警;业务;我的工作台;统计分析;日志管理;系统管理;
	@ApiModelProperty(value="业务模块 登陆;配置;监控;告警;业务;我的工作台;统计分析;日志管理;系统管理;",required=true)
	private String logBusinessNoduleName;
	// 前端传：日志发生的画面
	@ApiModelProperty(value="日志发生的画面",required=true)
	private String logFrameName;
	// 前端传：操作名称：例 修改操作；下发等
	@ApiModelProperty(value="操作名称：例 修改操作；下发等",required=true)
	private String logBusinessType;
	// 前端传：js函数名
	@ApiModelProperty(value="js函数名",required=true)
	private String logJsFunctioname;
	
	public String getLogBusinessNoduleName() {
		return logBusinessNoduleName;
	}
	public void setLogBusinessNoduleName(String logBusinessNoduleName) {
		this.logBusinessNoduleName = logBusinessNoduleName;
	}
	
	public String getLogFrameName() {
		return logFrameName;
	}
	public void setLogFrameName(String logFrameName) {
		this.logFrameName = logFrameName;
	}
	
	public String getLogBusinessType() {
		return logBusinessType;
	}
	public void setLogBusinessType(String logBusinessType) {
		this.logBusinessType = logBusinessType;
	}
	
	public String getLogJsFunctioname() {
		return logJsFunctioname;
	}
	public void setLogJsFunctioname(String logJsFunctioname) {
		this.logJsFunctioname = logJsFunctioname;
	}

}
