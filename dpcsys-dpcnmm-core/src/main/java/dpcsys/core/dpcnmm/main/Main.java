package dpcsys.core.dpcnmm.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import dpcsys.api.dpcnmm.model.DfieldCheck;
import dpcsys.api.dpcnmm.service.DfieldCheckService;
import faner.dplatformSpringjdbc.api.frame.command.db.springJDBC.xml.SQLBuilder;
import faner.dplatformSpringjdbc.api.frame.util.tools.file.FileCoreUtil;
import faner.dplatformSpringjdbc.api.frame.util.tools.json.JsonCoreUtil;

/**
 * 主方法，启动jar
 *
 * @author : lijianjun
 * @version V1.0
 * @Description: TODO
 * @Company:
 * @date: 2015年6月2日 上午10:03:42
 */
public class Main {
    public static void main(String[] args) throws IOException {
        readXML(args);
        startMain();
    }

    public static void readXML(String[] args) throws FileNotFoundException {
        List<File> fileList = null;
        if (args != null && args.length > 0) {
            System.out.println(args[0]);
            fileList = FileCoreUtil.getTempFileList(args[0]);
        } else {
            //fileList = FileCoreUtil.getTempFileList("src/main/resources/sqlxml");
            fileList = FileCoreUtil.getTempFileList("C:\\canglang\\java_project\\1\\dpcsys_test\\dpcsys-dpcnmm-core\\src\\main\\resources\\sqlxml");
        }
        SQLBuilder.readStream(fileList);
//		System.out.println(OverallSituationStatic.getTableAllSql("DfieldCheck"));
//		Table table = OverallSituationStatic.getTableAttribute("DfieldCheck");
//		System.out.println(JsonCoreUtil.toJSONString(table));
//		table = OverallSituationStatic.getTableAttribute("DfieldCheck");
//		System.out.println(JsonCoreUtil.toJSONString(table));
//		String sqlPath = "";
//		FileInputStream fin = null;
//		for(File file : fileList){
//			fin = new FileInputStream(file);
//			sqlPath += ";" + file.getAbsolutePath();
//			readStream(fin);
//		}
//		SQLBuilder sqlBuilder = new SQLBuilder(sqlPath.substring(1));
    }


    public static void startMain() {
        ClassPathXmlApplicationContext context = null;
        try {
            String[] contextLocations = new String[]{"classpath*:/app-context.xml"};
            System.out.println("进入main方法.....");
            System.out.println("加载完成配置文件.....");
            context = new ClassPathXmlApplicationContext(contextLocations);
            DfieldCheckService dfieldCheckService = (DfieldCheckService) context
                    .getBean("dfieldCheckService");
            List<DfieldCheck> list = dfieldCheckService.findByObj(null);
            System.out.println(JsonCoreUtil.toJSONString(list));
//			List<DfieldCheck> dfiList = new ArrayList<DfieldCheck>();
//			DfieldCheck dfieldCheck = new DfieldCheck();
//			dfieldCheck.setId(Long.valueOf(16));
//			dfieldCheck.setName("16161616161");
//			dfieldCheck.setRegular("dddddddddd");
//			dfieldCheck.setOwnedBusiness("cccccc");
//			dfieldCheck.setRemarks("dddddddddddd");
//			dfieldCheck.setDelFlg("1");
//			dfieldCheck.setInsUser("admin");
//			dfieldCheck.setInsTime(new Date());
//			dfieldCheck.setUpdUser("admin");
//			dfieldCheck.setUpdTime(new Date());
//			dfiList.add(dfieldCheck);

//			dfieldCheck = new DfieldCheck();
//			dfieldCheck.setId(Long.valueOf(17));
//			dfieldCheck.setName("17171717717");
//			dfieldCheck.setRegular("aaaaaaaaa");
//			dfieldCheck.setOwnedBusiness("ewfwa");
//			dfieldCheck.setRemarks("qqqqqq");
//			dfieldCheck.setDelFlg("1");
//			dfieldCheck.setInsUser("admin");
//			dfieldCheck.setInsTime(new Date());
//			dfieldCheck.setUpdUser("admin");
//			dfieldCheck.setUpdTime(new Date());
//			dfiList.add(dfieldCheck);

//			String saveSql = "INSERT INTO d_field_check(name,regular,del_flg,ins_user,ins_time,upd_user,upd_time) values(:name,:regular,:delFlg,:insUser,:insTime,:updUser,:updTime)";
//			int num = dfieldCheckService.save(saveSql, dfieldCheck);
//			System.out.println(num);

//			String saveSql = "INSERT INTO d_field_check(name,regular,del_flg,ins_user,ins_time,upd_user,upd_time) values(:name,:regular,:delFlg,:insUser,:insTime,:updUser,:updTime)";
//			String updateSql = "update d_field_check set name = :name where id = :id";
//			String deleteSql = "delete from d_field_check where id = :id";
//			Map<String,Object> paramsMap = new HashMap<String,Object>();
//			paramsMap.put("name", "232323232");
//			paramsMap.put("regular", "232323232");
//			paramsMap.put("delFlg", "1");
//			paramsMap.put("insUser", "232323232");
//			paramsMap.put("updUser", "232323232");
//			paramsMap.put("insTime", "2017-05-01");
//			paramsMap.put("updTime", "2017-05-01");
//			paramsMap.put("id", "25");
//			int num = dfieldCheckService.update(deleteSql, paramsMap);
//			System.out.println(num);

//			Map<String,Object> paramsMap = new HashMap<String,Object>();
//			paramsMap.put("name", "232323232");
//			dfiList = dfieldCheckService.findByObj(paramsMap);
//			System.out.println(JsonCoreUtil.toJSONString(dfiList));

//			Map<String,Object> paramsMap = new HashMap<String,Object>();
//			paramsMap.put("name", "232323232");
//			Map<String,Object> countMap = dfieldCheckService.count(paramsMap);
//			System.out.println(JsonCoreUtil.toJSONString(countMap));

//			Map<String,Object> paramsMap = new HashMap<String,Object>();
//			paramsMap.put("name", "232323232");
//			paramsMap.put("id", "23");
//			dfieldCheckService.updateDelete(paramsMap);


//			Map<String,Object> paramsMap = new HashMap<String,Object>();
//			paramsMap.put("name", "16161616161");
//			List<DfieldCheck> list = dfieldCheckService.getById(paramsMap);
//			System.out.println(JsonCoreUtil.toJSONString(list));

//			int id = dfieldCheckService.save(dfieldCheck);
//			System.out.println(id);
//			
//			int id = dfieldCheckService.update(dfieldCheck);
//			System.out.println(id);

//			int id = dfieldCheckService.delete(dfieldCheck);
//			System.out.println(id);

//			dfieldCheckService.deleteAll();

//			dfieldCheckService.batchSave(dfiList);

//			dfieldCheckService.batchUpdate(dfiList);

//			Integer[] ids = new Integer[]{16,17};
//			dfieldCheckService.batchDelete(ids);

//			dfieldCheck = dfieldCheckService.findById(16);
//			System.out.println(JsonCoreUtil.toJSONString(dfieldCheck));

//			dfiList = dfieldCheckService.findAll();
//			System.out.println(JsonCoreUtil.toJSONString(dfiList));

//			Map<String,Object> data = null;
//			String sql = "SELECT * FROM d_field_check where id = :id";
//			Map<String,Object> paramsMap = new HashMap<String,Object>();
//			paramsMap.put("id", 19);
//			data = dfieldCheckService.findOne(sql, paramsMap);
//			System.out.println(JsonCoreUtil.toJSONString(data));

//			List<Map<String,Object>> dataList = null;
//			String sql = "SELECT * FROM d_field_check";
//			Map<String,Object> paramsMap = new HashMap<String,Object>();
//			paramsMap.put("id", 19);
//			dataList = dfieldCheckService.findListMap(sql, null);
//			System.out.println(JsonCoreUtil.toJSONString(dataList));

//			String sql = "SELECT * FROM d_field_check where id = :id";
//			Map<String,Object> paramsMap = new HashMap<String,Object>();
//			paramsMap.put("id", 19);
//			dfieldCheck = dfieldCheckService.findForObject(sql, paramsMap);
//			System.out.println(JsonCoreUtil.toJSONString(dfieldCheck));

//			String sql = "SELECT * FROM d_field_check";
//			Map<String,Object> paramsMap = new HashMap<String,Object>();
//			paramsMap.put("id", 19);
//			dfiList = dfieldCheckService.findForList(sql, paramsMap);
//			System.out.println(JsonCoreUtil.toJSONString(dfiList));


//			String updateSQL = "UPDATE d_field_check set name = :name where id = :id";
//			Map<String,Object> paramsMap = new HashMap<String,Object>();
//			paramsMap.put("id", 19);
//			paramsMap.put("name", "update1111");
//			dfieldCheckService.execSQL(updateSQL, paramsMap);
//			String deleteSQL = "DELETE FROM  d_field_check where id = :id";
//			Map<String,Object> paramsMap = new HashMap<String,Object>();
//			paramsMap.put("id", 19);
//			paramsMap.put("name", "update1111");
//			dfieldCheckService.execSQL(deleteSQL, paramsMap);
//			String insertSQL = "INSERT INTO d_field_check(name,regular,owned_business,remarks,del_flg,ins_user,ins_time,upd_user,upd_time)"
//					+ " values(:name,:regular,:owned_business,:remarks,:del_flg,:ins_user,:ins_time,:upd_user,:upd_time)";
//			Map<String,Object> paramsMap = new HashMap<String,Object>();
//			paramsMap.put("name", "22222");
//			paramsMap.put("regular", "22222");
//			paramsMap.put("remarks", "22222");
//			paramsMap.put("owned_business", "22222");
//			paramsMap.put("del_flg", "1");
//			paramsMap.put("ins_user", "222");
//			paramsMap.put("ins_time", "2017-10-11");
//			paramsMap.put("upd_user", "22222");
//			paramsMap.put("upd_time", "2017-10-11");
//			dfieldCheckService.execSQL(insertSQL, paramsMap);

            // dfieldCheckService.findAllDfieldCheck(dfieldCheck,page);
            // dfieldCheckService.count(dfieldCheck);
            // List list = dfieldCheckService.getAll();
            // if(list != null){
            // System.out.println("登录成功！");
            // }else{
            // System.out.println("登录失败！");
            // }
            // context.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("core 服务已经启动！按任意键退出");
        while (!Thread.interrupted()) {
            System.out.println("core 服务睡眠中.....");
            try {
                Thread.sleep(1000 * 60 * 60 * 24 * 365);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("core 服务停止！.....");
    }
}
