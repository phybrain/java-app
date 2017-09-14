package team.bianming.javaapp.controller;

import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import team.bianming.javaapp.entity.News;
import team.bianming.javaapp.service.NewsService;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
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
    @RequestMapping("/newslist")
    public Page<News> ListNews(int pageSize, int pageNum, int categoryId) {

        return newsService.findNewsByCategory(pageNum,pageSize,categoryId);
    }

    @RequestMapping(value = "/hellos",method = RequestMethod.GET)
    public String hello(Model model) {
        model.addAttribute("hello", "spring");
        return "index";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if (!file.isEmpty()) {
            try {
                String filePath = request.getSession().getServletContext().getRealPath("imgupload/");
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(filePath));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            }
            return "上传成功";
        } else {
            return "上传失败，因为文件是空的.";
        }
    }
}