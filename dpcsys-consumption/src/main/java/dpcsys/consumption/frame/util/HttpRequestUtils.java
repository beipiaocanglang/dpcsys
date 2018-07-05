package dpcsys.consumption.frame.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

/**
 * Http远程调用工具类
 * author : sunpanhu
 * create time : 2018/5/11 上午11:05
 */
@SuppressWarnings({"all", "rawtypes"})
public class HttpRequestUtils {

    public static final String ERROR = "ERROR";

    /**
     * POST请求
     * @param client
     * @param url
     * @param params
     * @return
     * author : sunpanhu
     * createTime : 2018/5/11 上午11:07
     */
    public static String doPost(CloseableHttpClient client, String url, Map<String,Object> params) {

        if(null != client && StringUtils.isNotBlank(url)){

            String httpStr = "";
            HttpPost httpPost = new HttpPost(url);

            try {
                //创建一个提交数据的容器
                JSONObject json = new JSONObject();

                if(null != params && params.size() > 0){
                    for (Map.Entry<String, Object> entry : params.entrySet()) {
                        json.put(entry.getKey(), entry.getValue()==null?"":entry.getValue().toString());
                    }
                }

                StringEntity stringEntity = new StringEntity(json.toString(),"utf-8");

                //封装容器到请求参数中
                stringEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                //设置请求参数到post请求中
                httpPost.setEntity(stringEntity);
                httpPost.setHeader("Content-Type", "application/json");
                //执行post请求
                HttpResponse response = client.execute(httpPost);

                HttpEntity entity = response.getEntity();

                httpStr = EntityUtils.toString(entity, "utf-8");

            } catch (IOException e) {
                e.printStackTrace();
                return ERROR;
            } finally {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return httpStr;
        }
        return ERROR;
    }
}
