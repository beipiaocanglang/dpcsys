package dpcsys.api.dpcnmm.model.constant;

/**
 * 卡类型
 * author : sunpanhu
 * create time : 2018-5-25 上午9:50
 */
public enum CardTypeEnum {
    BJ_CARD(0, "BJ_CARD", "白金卡"),
    J_CARD(1, "J_CARD", "金卡"),
    Y_CARD(2, "Y_CARD", "银卡"),
    VIP_CARD(3, "VIP_CARD", "VIP卡"),
    ZZ_CARD(4, "ZZ_CARD", "至尊卡"),
    JZ_CARD(5, "JZ_CARD", "金尊卡");

    private Integer code;
    private String value;
    private String desc;

    CardTypeEnum(Integer code, String value, String desc) {
        this.code = code;
        this.value = value;
        this.desc = desc;
    }

    //根据code查询
    public static CardTypeEnum getByCode(Integer code) {
        for (CardTypeEnum type : CardTypeEnum.values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        return null;
    }
    //根据value查询
    public static CardTypeEnum getByValue(String value) {
        for (CardTypeEnum type : CardTypeEnum.values()) {
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
