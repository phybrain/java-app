package team.bianming.javaapp.controller;

import com.github.pagehelper.Page;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import team.bianming.javaapp.Util.FileUtil;
import team.bianming.javaapp.entity.News;
import team.bianming.javaapp.service.NewsService;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by xiaopeng on 2017/9/14.
 */
@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    NewsService newsService;
    @ResponseBody
    @RequestMapping("/newslist")
    public Page<News> ListNews(int pageSize, int pageNum, int categoryId) {

        return newsService.findNewsByCategory(pageNum,pageSize,categoryId);
    }

    @ResponseBody
    @RequestMapping("/deleteNews")
    public int deleteNews(News news){

        return newsService.DeleteNews(news);
    }
    @ResponseBody
    @RequestMapping("/updateNews")
    public int updateNews(News news){

        return newsService.UpdateNews(news);
    }
    @RequestMapping(value = "/newsindex",method = RequestMethod.GET)
    public String hello(Model model) {
        model.addAttribute("hello", "spring");
        return "index";
    }
    @ResponseBody
    @RequestMapping(value="/upload", method = RequestMethod.POST)
    public  String uploadImg(@RequestParam("file") MultipartFile file) {

        String fileName = file.getOriginalFilename();

        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(!path.exists()) path = new File("");
        File upload = new File(path.getAbsolutePath(),"static\\upload\\");
        if(!upload.exists())
            upload.mkdirs();
        System.out.println("upload url:"+upload.getAbsolutePath());
        String filePath=upload.getAbsolutePath();

        try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
            return "上传失败"+e.getMessage();
        }
        //返回json
        return "uploadimg success";
    }


}