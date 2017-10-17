package team.bianming.javaapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import team.bianming.javaapp.entity.User;
import team.bianming.javaapp.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by xiaopeng on 2017/9/10.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;


    @RequestMapping("/")
    String home() {
        return "index";
    }

    @ResponseBody
    @RequestMapping("/deleteUser")
    public int deleteUser(User user){

        return userService.DeleteUser(user);
    }

    @ResponseBody
    @RequestMapping("/userList")
    public List<User> findPageUser(int pageSize, int pageNum){

        return userService.findUserByPage(pageNum,pageSize);
    }

    @ResponseBody
    @RequestMapping("/login")
    public String login(HttpSession session,String name,String password){
        User user = userService.login(name,password);
        if(user!=null){
            session.setAttribute("userid",user.getId());
            return "true";
        }
        return "false";
    }

}
