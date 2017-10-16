package team.bianming.javaapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import team.bianming.javaapp.entity.AiAnswer;

/**
 * Created by ckwin8 on 2017/10/16.
 */

@RequestMapping("ai")
@Controller
public class AIController {
    @Autowired
    private RestTemplate restTemplate;

    @ResponseBody
    @RequestMapping("/ask")
    public String ask(String question){
        return restTemplate.getForObject("http://192.168.1.101:8000/chat/"+question, AiAnswer.class).getAnswer();
    }

}
