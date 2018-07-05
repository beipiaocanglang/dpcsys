package dpcsys.api.dpcnmm.model.constant;

/**
 * 停车类型
 * author : sunpanhu
 * create time : 2018-5-25 上午9:52
 */
public enum ParkTypeEnum {
    LONG_TERML(0, "LONG","长期停车"),
    SHORT_TERM(1, "SHORT","短期停车"),
    MERGE(2, "MERGE","合并停车方案");

    private Integer code;
    private String value;
    private String desc;

    ParkTypeEnum(Integer code, String value, String desc) {
        this.code = code;
        this.value = value;
        this.desc = desc;
    }

    //根据code查询
    public static ParkTypeEnum getByCode(Integer code) {
        for (ParkTypeEnum type : ParkTypeEnum.values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return null;
    }
    //根据value查询
    public static ParkTypeEnum getByValue(String value) {
        for (ParkTypeEnum type : ParkTypeEnum.values()) {
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
