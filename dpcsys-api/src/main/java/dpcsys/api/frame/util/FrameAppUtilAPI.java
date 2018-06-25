package dpcsys.api.frame.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

abstract public class FrameAppUtilAPI {
	
	private static Logger logger = Logger.getLogger(FrameAppUtilAPI.class);
	
	private static Map<String,String> sysConfig = new HashMap<String, String>();

	/** 读取系统配置文件属性*/
	public static Map<String,String> getSysConfig(){
		return sysConfig;
	}
	
	/** 加载属性配置文件 **/
	@SuppressWarnings("rawtypes")
	public static Map<String,String> loadConfig(String filePath){
		// 1.读取来自jdbc文件的配置,并且放入configMap内,应用程序共同使用
		
		// 加载属性文件对象
		Properties props = null;
		// 文件流
		FileInputStream fis = null;
		// 文件读取流
		Reader reader = null;
		try {
			// 配置文件夹路径
			File cofFile = new File(filePath);
			// 配置文件夹下的所有文件名称
			String[] cofFileList = cofFile.list();
			String configFilePath = "";
			if(cofFileList != null && cofFileList.length > 0){
				for(int i = 0; i < cofFileList.length; i++){
					// 某一个配置文件
					configFilePath = filePath + "//" + cofFileList[i];
					// 只有以.properties结尾，以config.开头或者jdbc.开头的文件夹才被加载
					if(configFilePath.contains(".properties")
							&& (configFilePath.contains("config.") || configFilePath.contains("jdbc."))
							){
						props = new Properties();
						fis = new FileInputStream(configFilePath);
						reader = new InputStreamReader(fis, "UTF-8");
						props.load(reader);
						Iterator it = props.keySet().iterator();
						while (it.hasNext()) {
							String key = (String) it.next();
							sysConfig.put(key, props.get(key) != null ? props.get(key).toString() : "");
						}
					}
				}
			}
			return sysConfig;
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			return null;
		}finally{
				try {
					if(fis != null) fis.close();
					if(reader != null) reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
}
