package team.bianming.javaapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xiaopeng on 2017/9/15.
 */
@Controller
public class PageController {


@RequestMapping("/public_left_admin.html")
    public String PublicLeft(){

    return "public_left_admin";
}
    @RequestMapping("/public_header.html")
    public String PublicHeader(){

        return "/public_header";
    }

    @RequestMapping("/wenzhang_xinwen.html")
    public String News(){

        return "wenzhang_xinwen";
    }
    @RequestMapping("/wenzhang_aboutus.html")
    public String AddNews(){

        return "wenzhang_aboutus";
    }

    @RequestMapping("/wenzhang_xinwen_fabu.html")
    public String AddNewsHtml(){

        return "wenzhang_xinwen_fabu";
    }

    @RequestMapping("/kefuguanli.html")
    public String KefuManage(){

        return "kefuguanli";
    }

    @RequestMapping("/admin/login.html")
    public String loginAdmin(){

        return "loginAdmin";
    }

    @RequestMapping("/CS/login.html")
    public String loginCS(){

        return "login_kefu";
    }
    @RequestMapping("/knowledge.html")
    public String knowledge(){

        return "knowledge";
    }
    @RequestMapping("/user.html")
    public String userManage(){

        return "user";
    }


    @RequestMapping("/work_statistics.html")
    public String work(){

        return "work_statistics";
    }
}
