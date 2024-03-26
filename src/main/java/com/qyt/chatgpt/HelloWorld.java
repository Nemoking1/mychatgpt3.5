package com.qyt.chatgpt;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class HelloWorld {

    @RequestMapping("helloworld")
    public String helloWorld() {
        return "Hello World";
    }
}
