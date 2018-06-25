package dpcsys.consumption.frame.sys;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import dpcsys.consumption.frame.sys.bean.ManagerOptor;

/**
 * @author : lijianjun
 * @version V1.0
 * @Description: 应用管理器(用于登录 、 在线用户信息获取)
 * @Company:Justin
 * @date: 2015年11月20日 下午7:49:49
 */
@SuppressWarnings("rawtypes")
public class AppManager {
    private static Logger logger = Logger.getLogger(AppManager.class);

    //(key-value)=(sessionid-userId)
    private static Map seIDUserIdMap = new HashMap();
    //(key-value)=(userId-BaseSysUser)
    private static Map userIdMuserMap = new HashMap();
    private static String userName = "";

    //由于本系统要求一个用户可以多会话登录，所以把该map失效，在一个用户只能有一个会话的系统中，这个map非常有用
    //private static HashMap usermap = new HashMap(); //(key-value)=(userId-sessionid)

    /**
     * 〇**登录成功时，设置session信息
     *
     * @param request
     * @param managerOptor
     */
    public static void setSessionInfo(HttpServletRequest request, ManagerOptor managerOptor) {
        HttpSession session = request.getSession();
        if (isOnline(session)) {
            remove(session);
        }
        String userId = managerOptor.getUserId();
//        if (isOnline(userId)) {//一个用户名只能有一个会话
//             remove(userId);
//        }

        // 将信息保存在SESSION管理器中
        put(userId, session.getId(), managerOptor);

        logger.info("-----login : 登录成功后，把用户相关信息放到session！");
    }

    /**
     * 添加用户信息
     *
     * @param userId       操作员工号
     * @param sessionID    sessionid
     * @param ManagerOptor managerOptor
     */
    @SuppressWarnings("unchecked")
    public static void put(String userId, String sessionID, ManagerOptor managerOptor) {

        seIDUserIdMap.put(sessionID, userId);
        userIdMuserMap.put(userId, managerOptor);

    }

    //********************************Up: logining loading(save RAM)***********************************

    /**
     * ① **根据session信息，获得当前用户信息
     *
     * @param session HttpSession
     * @return ManagerOptor
     */
    public static ManagerOptor getAuthorization(HttpSession session) {

        ManagerOptor managerOptor = getAuthorization(getUserIdBySe(session));

        return managerOptor;
    }

    /**
     * 根据session信息，获得userID
     */
    public static String getUserIdBySe(HttpSession session) {

        return isOnline(session) ? getUserIdBySe1(session) : null;
    }

    /**
     * 判断操作员是否在线
     */
    public static boolean isOnline(HttpSession session) {
        return seIDUserIdMap.keySet().contains((session.getId()));
    }

    /**
     * 根据session获得userID
     */
    public static String getUserIdBySe1(HttpSession session) {
        String userId = (String) seIDUserIdMap.get(session.getId());
        return userId;
    }

    /**
     * ② **根据userId信息，获得当前用户信息
     *
     * @param session HttpSession
     * @return ManagerOptor
     */
    public static ManagerOptor getAuthorization(String userId) {
        ManagerOptor managerOptor = (ManagerOptor) userIdMuserMap.get(userId);

        return managerOptor;
    }

    /**
     * ③ **根据request信息，获得当前用户信息
     *
     * @param session HttpSession
     * @return ManagerOptor
     */
    public static ManagerOptor getAuthorization(HttpServletRequest request) {
        ManagerOptor managerOptor = getAuthorization(getUserIdBySe(request.getSession()));

        return managerOptor;
    }

    //根据userId,从内存中删除用户对象信息
    public static void removeManagerOptor(String userId) {
        userIdMuserMap.remove(userId);
    }

    //从内存中删除所有用户对象
    public static void removeAllManagerOptor() {
        userIdMuserMap.clear();
    }

//    /**
//     * ④ **根据request信息，获得当前简单用户信息
//     * @param session HttpSession
//     * @return ManagerOptor
//     */
//    public static TblAccountsUsers getUserBySe(HttpSession session) {
//    		ManagerOptor managerOptor  = getAuthorization(session);
//    		
//    		return managerOptor!=null?managerOptor.getSysUser():null;
//    }  

    /**
     * 通过userId获取 Remote Host
     *
     * @param userId 工号
     * @return RemoteHost
     */
    public String getRemoteHost(String userId) {

        ManagerOptor managerOptor = getAuthorization(userId);
        return managerOptor != null ? managerOptor.getRemoteHost() : null;
    }

    /**
     * 根据userId 删除用户信息
     *
     * @param userId 操作员工号
     */
    public static void remove(String userId) {
        if (userId != null) {
            // usermap.remove(userId);
            removeManagerOptor(userId);
        }
    }

    /**
     * 根据session 删除用户信息
     *
     * @param session HttpSession
     */
    public static void remove(HttpSession session) {
        String userId = getUserIdBySe(session);
        if (userId != null) {
            remove(userId);
        }
    }


    /**
     * 将所有用户置为离线
     */
    public static void removeAll() {
        removeAllManagerOptor();
    }

    public void remove2(HttpSession session) {
        seIDUserIdMap.remove(session.getId());
    }

    /**
     * ⑤ **获取再现用户数
     *
     * @param session HttpSession
     * @return ManagerOptor
     */
    public static int size() {
        int num = seIDUserIdMap.size();

        return num;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        AppManager.userName = userName;
    }
}
