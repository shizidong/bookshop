package crh.mars.study.contorller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class File {

    /**
     * index.html页面
     * @return
     */
    @RequestMapping("/file")
    public String index(){
        return "fileUpload";
    }
}
