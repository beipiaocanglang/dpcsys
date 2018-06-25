package dpcsys.api.dpcnmm.constants;

import java.util.HashMap;

public class DpcnmmResponseHashMap extends HashMap<String, Object> {

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
     * 返回对象信息
     */
    private Object data;
    /**
     * 总条数
     */
    private int count;

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
    public DpcnmmResponseHashMap() {
        this.code = DpcnmmResponseConstant.CODE;
        this.msg = DpcnmmResponseConstant.MSG;
        inits();
    }

    /**
     * 带参构造，初始化构造函数
     */
    public DpcnmmResponseHashMap(Integer code, String msg) {
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.put("count", count);
        this.count = count;
    }
}
