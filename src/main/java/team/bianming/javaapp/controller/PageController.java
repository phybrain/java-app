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

}
