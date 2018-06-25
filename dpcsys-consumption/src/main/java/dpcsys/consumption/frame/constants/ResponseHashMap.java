package dpcsys.consumption.frame.constants;

import java.util.HashMap;

/**
 * 请求返回值父类，直接new该对象。
 *
 * @version V1.0
 * @Description: 返回值父类
 * @Company:Justin
 * @author: lijianjun
 * @date: 2016年4月25日 下午7:44:41
 */
public class ResponseHashMap extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    /**
     * 返回码
     **/
    private Integer code;
    /**
     * 返回消息
     **/
    private String msg;
    /**
     * 返回数据
     **/
    private Object data;


    /**
     * 写入map
     *
     * @method: 写入map
     * @Description: 写入map
     * @author: lijianjun
     * @date 2016年4月25日 下午7:45:36
     */
    public void inits() {
        put("code", code);
        put("msg", msg);
    }

    /**
     * 无参构造，初始化构造函数
     */
    public ResponseHashMap() {
        this.code = ResponseConstant.CODE;
        this.msg = ResponseConstant.MSG;
        inits();

    }

    /**
     * 带参构造，初始化构造函数
     */
    public ResponseHashMap(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        inits();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.put("code", code);
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.put("msg", msg);
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.put("data", data);
        this.data = data;
    }
}
