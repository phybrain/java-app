package team.bianming.javaapp.controller;

import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import team.bianming.javaapp.entity.QA;
import team.bianming.javaapp.service.QAService;

/**
 * Created by xiaopeng on 2017/10/1.
 */
@Controller
@RequestMapping("/know")
public class QAController {

    @Autowired
    QAService qaService;

    @ResponseBody
    @RequestMapping("/QAlist")
    public Page<QA> ListNews(int pageSize, int pageNum) {

        return qaService.findQAByPage(pageNum,pageSize);
    }

    @ResponseBody
    @RequestMapping("/searchQAlist")
    public Page<QA> ListQA(int pageSize, int pageNum,String s) {

        return qaService.findQAByPage(pageNum,pageSize);
    }

    @ResponseBody
    @RequestMapping("/deleteKnow")
    public int deleteQA(QA qa){

        return qaService.DeleteQA(qa);
    }
    @ResponseBody
    @RequestMapping("/updateKnow")
    public int updateQA(QA qa){

        return qaService.UpdateQA(qa);
    }

    @ResponseBody
    @RequestMapping("/saveKnow")
    public int saveQA(QA qa){

        return qaService.SaveQA(qa);
    }
}
