package team.bianming.javaapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xiaopeng on 2017/9/10.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @ResponseBody
    @RequestMapping("/")
    String home() {
        return "index";
    }

    @ResponseBody
    @RequestMapping("/hello/{myName}")
    String index(@PathVariable String myName) {
        return "Hello "+myName+"!!!";
    }

}
