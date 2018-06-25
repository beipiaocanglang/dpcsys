package dpcsys.consumption.dpcnmm.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import dpcsys.api.dpcnmm.constants.DpcnmmResponseConstant;
import dpcsys.api.dpcnmm.constants.DpcnmmResponseHashMap;
import dpcsys.api.frame.listener.AppUtil;
import dpcsys.consumption.frame.web.controller.BaseController;
import faner.dplatformSpringjdbc.api.frame.util.tools.file.FileCoreUtil;
import faner.dplatformSpringjdbc.api.frame.util.tools.json.JsonCoreUtil;
import faner.dplatformSpringjdbc.api.frame.util.tools.object.StringCoreUtil;
import faner.dplatformSpringjdbc.api.frame.util.tools.sys.SystemCoreUtil;

@Controller
@Scope(value = "prototype")
@RequestMapping("/uploadFile")
public class UploadFileController extends BaseController {


    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    /**
     * 多文件上传
     *
     * @param files 文件集合
     * @method: uploads
     * @Description: TODO
     * @author : lijianjun
     * @date 2017年3月27日 下午1:11:07
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/uploads")
    public void uploads(@RequestParam("file") MultipartFile[] files, String loginName, String timeStamp) {
        DpcnmmResponseHashMap map = new DpcnmmResponseHashMap();
        // 需返回的数据集合
        List<Map> fileList = new ArrayList<Map>();
        // 数据对象
        Map fileMap = null;
        try {
            if (files != null && files.length > 0 && StringCoreUtil.isNotEmpty(loginName) && StringCoreUtil.isNotEmpty(timeStamp)) {
                // 获取文件上传的真实路径
                String tomcatPath = SystemCoreUtil.getTomcatPath(this);
                // 文件夹备份目录
                String destDir = AppUtil.getSysConfig().get("file_upload_path");
                // 获取可访问的路径地址
                String requestPath = AppUtil.getSysConfig().get("file_request_path");
                // 获取请求地址
                String httpPath = AppUtil.getSysConfig().get("httpFileUrl");
                // 绝对路径
                String sellerPath = tomcatPath + destDir + loginName + File.separator;
                String file_request_path = tomcatPath + requestPath + loginName + File.separator;
                String httpFileUrl = httpPath + loginName + "/";
                if (StringCoreUtil.isNotEmpty(loginName)) {
                    sellerPath += timeStamp + File.separator;
                    file_request_path += timeStamp + File.separator;
                    httpFileUrl += timeStamp + "/";
                }
                // 判断是否有文件夹，没有则创建
                File destFile = new File(sellerPath);
                if (!destFile.exists()) {
                    destFile.mkdirs();
                }
                File fileRequestPath = new File(file_request_path);
                if (!fileRequestPath.exists()) {
                    fileRequestPath.mkdirs();
                }
                String fileName = "";
                //上传文件过程
                for (MultipartFile file : files) {
                    fileMap = new HashMap();
                    // 重命名文件名称
                    fileName = sdf.format(new Date()) + file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
                    File f = new File(destFile.getAbsoluteFile() + File.separator + fileName);
                    //如果当前文件已经存在了，就跳过。
                    if (f.exists()) {
                        continue;
                    }
                    file.transferTo(f);
                    f.createNewFile();
                    // 复制到可访问路径地址
                    FileCoreUtil.copyFile(f, new File(file_request_path + File.separator + fileName));
                    fileMap.put("filePath", httpFileUrl + fileName);
                    fileMap.put("name", file.getOriginalFilename());
                    fileList.add(fileMap);
                }
                map.setData(fileList);
            } else {
                map = new DpcnmmResponseHashMap(DpcnmmResponseConstant.CODE_PARAM_ERROR, DpcnmmResponseConstant.MSG_PARAM_ERROR);
            }
        } catch (Exception e) {
            map = new DpcnmmResponseHashMap(DpcnmmResponseConstant.CODE_SYSTEM_ERROR, DpcnmmResponseConstant.MSG_SYSTEM_ERROR);
        }
        JsonCoreUtil.outPutJson(this.getResponse(), JsonCoreUtil.toJSONString(map));
    }
}
