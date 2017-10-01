package team.bianming.javaapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ckwin8 on 2017/9/13.
 */

@RestController
public class TestController {//测试用的

    @RequestMapping("/test/filter")
    public void testFilter(String content){
        System.out.println(content);
    }
}
