package com.qyt.chatgpt;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//测试图片显示
@Controller
public class ImgController {
    @GetMapping("/testimg")
    public String getImg(){
        return "testimg";
    }
}
