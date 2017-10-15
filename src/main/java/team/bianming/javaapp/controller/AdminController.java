package team.bianming.javaapp.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import team.bianming.javaapp.entity.Admin;
import team.bianming.javaapp.entity.User;

/**
 * Created by xiaopeng on 2017/10/10.
 */
@RequestMapping("admin")
@Controller
public class AdminController {

    @RequestMapping("/loginAdmin")
    public String gotoLogin(){
        return "loginAdmin";
    }

    @ResponseBody
    @RequestMapping("/index")
    public String adminIndex(Admin admin){
        if (admin.getName()=="admin")
        return "success";
        else return "登录失败";
    }
}
