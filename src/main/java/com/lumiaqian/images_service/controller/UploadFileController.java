package com.lumiaqian.images_service.controller;

import com.lumiaqian.images_service.Model.IpDir;
import com.lumiaqian.images_service.Model.Response;
import com.lumiaqian.images_service.Model.ResultCode;
import com.lumiaqian.images_service.Service.IpDirRepository;
import com.lumiaqian.images_service.Utils.IPUtil;
import com.lumiaqian.images_service.Utils.SnowFlake;
import com.lumiaqian.images_service.Utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @author qian
 * @version V1.0
 * @Title: UploadFileController
 * @Package: com.lumiaqian.iamges_service.controller
 * @Description: TOTO
 * @date 2019-04-28 15:22
 **/
@RestController()
public class UploadFileController {
    @Value("${upload.path}")
    private String path;
    @Value("${upload.url}")
    private String url;
    private String gif = ",gif";
    private String jpg = ".jpg";
    private String jpeg = ".jpeg";
    private String png = ".png";
    private final static Logger logger = LoggerFactory.getLogger(UploadFileController.class);
    @Autowired
    private IpDirRepository ipDirRepository;

    @PutMapping("upload/file")
    public Response fileUpload(MultipartFile file, HttpServletRequest request) {
        Response response;
        logger.debug("文件路径：" + path);
        if (file.isEmpty()) {
            response = new Response(ResultCode.File_Empty);
            return response;
        }
        String ip = IPUtil.getIpAddress(request);
        //根据IP生成目录
        String dir = generateDir(ip);
        logger.info("dir:"+dir);
        String filename = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = filename.substring(filename.lastIndexOf("."));
        long fileSize = file.getSize();
        String currentPath = path + "/" + dir;
        logger.info("文件名称" + filename + "-------文件大小" + fileSize + "-------文件路径" + currentPath + "文件后缀" + suffixName);
        if (!png.equals(suffixName) &&
                !gif.equals(suffixName) &&
                !jpeg.equals(suffixName) &&
                !jpg.equals(suffixName)) {
            // 校验文件格式
            response = new Response(ResultCode.File_Format_ERROR);
            return response;
        }
        /*SnowFlake snowFlake = new SnowFlake(2, 3);
        filename = snowFlake.nextId() + suffixName;
        logger.info("动态生成的文件名为：" + filename);*/
        File dest = new File(currentPath + "/" + filename);
        if (!dest.getParentFile().exists()) {
            //父目录不存在就创建一个
            dest.getParentFile().mkdir();
        }
        //保存文件
        try {
            file.transferTo(dest);
            String currentUrl = url + "/" + dir + "/" + filename;
            response = new Response();
            response.setData(currentUrl);
            response.setMessage("文件上传成功！");
        } catch (IOException e) {
            response = new Response(ResultCode.SYS_ERROR);
            response.setMessage("文件上传失败！");
            e.printStackTrace();
        }

        return response;
    }

    @GetMapping("test")
    public Response test() {
        Response response = new Response();
        return response;
    }

    private String generateDir(String ip) {
        String dir = "";
        IpDir ipDir = ipDirRepository.findByIP(ip);
        if (ipDir == null) {
            // 数据库中不存在对应的ipDir,那就生成新的对应文件夹,并存入数据库
            dir = StringUtil.getRandomString(6);
            ipDir = new IpDir();
            ipDir.setIp(ip);
            ipDir.setDir(dir);
            ipDirRepository.save(ipDir);
        } else {
            dir = ipDir.getDir();
        }
        return dir;
    }
}
