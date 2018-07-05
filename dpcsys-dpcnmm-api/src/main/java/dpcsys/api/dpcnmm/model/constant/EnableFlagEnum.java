package dpcsys.api.dpcnmm.model.constant;

/**
 * 当前卡对应的短期停车配置是否可用
 * author : sunpanhu
 * create time : 2018-5-25 上午9:53
 */
public enum EnableFlagEnum {
    EXPIRE(0, "NORMAL", "过期"),
    NORMAL(1, "EXPIRE", "正常");

    private Integer code;
    private String value;
    private String desc;

    EnableFlagEnum(Integer code, String value, String desc) {
        this.code = code;
        this.value = value;
        this.desc = desc;
    }

    //根据code查询
    public static EnableFlagEnum getByCode(Integer code) {
        for (EnableFlagEnum type : EnableFlagEnum.values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return null;
    }
    //根据value查询
    public static EnableFlagEnum getByValue(String value) {
        for (EnableFlagEnum type : EnableFlagEnum.values()) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        return null;
    }
    
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
}
