package dpcsys.consumption.frame.web.controller;

import org.apache.log4j.Logger;

import faner.dplatformSpringjdbc.api.frame.util.tools.page.PageBean;

/**
 * web层使用基类controller
 *
 * @author : lijianjun
 * @version V1.0
 * @Description: TODO
 * @Company:
 * @date: 2015年6月2日 上午10:00:45
 */
public class PubController {

    protected static final String LIST = "list";
    protected static final String STATUS = "status";
    protected static final String WARN = "warn";
    protected static final String ERROR = "error";
    protected static final String MESSAGE = "message";
    protected static final String CONTENT = "content";
    protected static final String AJAX = "ajax";
    protected static final String MSG = "msg";

    /**
     * json字符串，默认为
     */
    protected String jsonString = "{success:true}";

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

    public String getJsonString() {
        return jsonString;
    }

    /**
     * 日志
     */
    private Logger logger = Logger.getLogger(this.getClass());

    /**
     * 分页信息
     */
    public PageBean page = new PageBean();

    /**
     * Struts2 Action 返回的字符串
     */
    public String forward;

    /**
     * 显示信息页 是否是modaiDialog
     */
    private String modaiDialog;

    /**
     * 信息页执行脚本的内容
     */
    private String scriptContent;

    /**
     * 信息页返回的数据
     */
    private String returnValue;

    /**
     * 信息页显示的信息
     */
    private String msg;

    /**
     * 信息也跳转地址
     */
    private String url;

    public String getModaiDialog() {
        return modaiDialog;
    }

    public void setModaiDialog(String modaiDialog) {
        this.modaiDialog = modaiDialog;
    }

    public String getScriptContent() {
        return scriptContent;
    }

    public void setScriptContent(String scriptContent) {
        this.scriptContent = scriptContent;
    }

    public String getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public PageBean getPage() {
        return page;
    }

    public void setP(PageBean page) {
        this.page = page;
    }

    public String getForward() {
        return forward;
    }

    public void setForward(String forward) {
        this.forward = forward;
    }

    public Logger getLogger() {
        return logger;
    }

}
