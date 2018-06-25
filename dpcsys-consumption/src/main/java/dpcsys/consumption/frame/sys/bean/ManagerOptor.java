package dpcsys.consumption.frame.sys.bean;

import java.io.Serializable;


/**
 * @author : lijianjun
 * @version V1.0
 * @Description: 存放用户信息
 * @Company:Justin
 * @date: 2015年11月20日 下午7:48:42
 */
public class ManagerOptor implements Serializable {
    private static final long serialVersionUID = 8825343047384503763L;

    private String userId;//user ID
    private String remoteHost;//Ip
    private int port;//Port number

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRemoteHost() {
        return remoteHost;
    }

    public void setRemoteHost(String remoteHost) {
        this.remoteHost = remoteHost;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
