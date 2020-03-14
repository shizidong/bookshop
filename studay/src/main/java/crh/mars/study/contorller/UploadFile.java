package crh.mars.study.contorller;
import crh.mars.study.base.BaseException;
import crh.mars.study.enty.Resource;
import crh.mars.study.service.IResourceService;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;


@RestController
public class UploadFile {
    @Autowired
    private IResourceService resourceService;
    @ResponseBody
    @RequestMapping("/uploadFile")
    public JSON uploadFile(MultipartFile file, HttpServletRequest request) {

        JSONObject json=new JSONObject();
        String filePath = "D:/uploadFile/";//上传到这个文件夹
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdirs();
        }
        String finalFilePath = filePath + file.getOriginalFilename().trim();
        File desFile = new File(finalFilePath);
        System.out.print("路径为"+finalFilePath);
        String token="false";
        token = RandomStringUtils.randomAlphanumeric(32);
        Resource resource = new Resource();
        resource.setResId(token);
        resource.setResStatus(0);
        resource.setResAddress(finalFilePath);
        try {
            resourceService.saveRes(resource);
        } catch (BaseException e) {
            e.printStackTrace();
        }
        try {
            file.transferTo(desFile);
            json.put("code",0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            json.put("code",1);
        }
        return json;
    }
}

