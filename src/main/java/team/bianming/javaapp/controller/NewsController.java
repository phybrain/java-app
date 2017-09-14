package team.bianming.javaapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import team.bianming.javaapp.entity.News;

/**
 * Created by xiaopeng on 2017/9/14.
 */
@Controller
public class NewsController {

    @ResponseBody
    @RequestMapping()
    public News ListNews(int pageSize,int pageNum,String category){

        return null;
    }
}
