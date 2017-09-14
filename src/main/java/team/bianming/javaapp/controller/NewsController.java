package team.bianming.javaapp.controller;

import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import team.bianming.javaapp.entity.News;
import team.bianming.javaapp.service.NewsService;

import java.util.Map;

/**
 * Created by xiaopeng on 2017/9/14.
 */
@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    NewsService newsService;
    @ResponseBody
    @RequestMapping()
    public Page<News> ListNews(int pageSize, int pageNum, int categoryId) {

        return newsService.findNewsByCategory(pageNum,pageSize,categoryId);
    }

    @RequestMapping(value = "/hellos",method = RequestMethod.GET)
    public String hello(Model model) {
        model.addAttribute("hello", "spring");
        return "index";
    }

}