package dpcsys.api.dpcInterface.constants;

public class DpcInterfaceResponseConstant {
    /**
     * 正常
     */
    public static int CODE = 2000;
    /**
     * 正常
     */
    public static String MSG = "OK";

    /**
     * 参数错误
     */
    public static int CODE_PARAM_ERROR = 2001;
    /**
     * 参数错误
     */
    public static String MSG_PARAM_ERROR = "参数错误!";

    /**
     * 正常无结果
     */
    public static int CODE_NULL = 2002;
    /**
     * 正常无结果
     */
    public static String MSG_NULL = "无数据!";

    /**
     * 系统错误
     */
    public static int CODE_SYSTEM_ERROR = 2004;
    /**
     * 系统错误
     */
    public static String MSG_SYSTEM_ERROR = "系统异常!";

    /**
     * token错误
     */
    public static int CODE_TOKEN_ERROR = 2003;
    /**
     * token错误
     */
    public static String MSG_TOKEN_ERROR = "令牌错误!";
}
