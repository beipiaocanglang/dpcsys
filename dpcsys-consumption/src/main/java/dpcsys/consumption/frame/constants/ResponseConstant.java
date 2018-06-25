package dpcsys.consumption.frame.constants;

/**
 * 请求返回常量类属性值
 *
 * @version V1.0
 * @Description: 请求返回常量类属性值
 * @Company:Justin
 * @author: lijianjun
 * @date: 2016年4月25日 下午7:46:04
 */
public class ResponseConstant {

    /**
     * 正常
     */
    public static int CODE = 20000;
    /**
     * 正常
     */
    public static String MSG = "OK";

    /**
     * 参数错误
     */
    public static int CODE_PARAM_ERROR = 20001;
    /**
     * 参数错误
     */
    public static String MSG_PARAM_ERROR = "参数错误!";

    /**
     * 签名错误
     */
    public static int CODE_AUTOGRAPH_ERROR = 20002;
    /**
     * 签名错误
     */
    public static String MSG_AUTOGRAPH_ERROR = "签名错误!";

    /**
     * token错误
     */
    public static int CODE_TOKEN_ERROR = 20003;
    /**
     * token错误
     */
    public static String MSG_TOKEN_ERROR = "令牌错误!";

    /**
     * 系统错误
     */
    public static int CODE_SYSTEM_ERROR = 20004;
    /**
     * 系统错误
     */
    public static String MSG_SYSTEM_ERROR = "系统异常!";
}
