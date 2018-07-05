package dpcsys.api.dpcnmm.model.constant;

/**
 * author : sunpanhu
 * create time : 2018-6-4 下午2:51
 */
public enum SchemeEnum {
    LONG_TERM_SCHEME(0, "LONG_TERM", "使用长期停车方案"),
    SHORT_TERM_SCHEME(1, "SHORT_TERM", "使用短期停车方案"),
    MERGE_SCHEME(2, "MERGE", "合并停车方案");

    private Integer code;
    private String value;
    private String desc;

    SchemeEnum(Integer code, String value, String desc) {
        this.code = code;
        this.value = value;
        this.desc = desc;
    }

    //根据code查询
    public static SchemeEnum getByCode(Integer code) {
        for (SchemeEnum type : SchemeEnum.values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return null;
    }
    //根据value查询
    public static SchemeEnum getByValue(String value) {
        for (SchemeEnum type : SchemeEnum.values()) {
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
