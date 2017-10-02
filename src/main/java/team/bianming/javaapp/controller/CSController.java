package team.bianming.javaapp.controller;

import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import team.bianming.javaapp.Util.FileUtil;
import team.bianming.javaapp.entity.CustomService;
import team.bianming.javaapp.service.CSService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by ckwin8 on 2017/10/1.
 */

@Controller
@RequestMapping("/cs")
public class CSController {

    @Autowired
    CSService csService;

    @ResponseBody
    @RequestMapping(value="/create", method = RequestMethod.POST)
    public String createCS(String num,String name,String time,String weekdays,@RequestParam("file") MultipartFile file){
        //预处理参数
        if(StringUtils.isEmpty(num)){
            return "缺少工号";
        }
        if(StringUtils.isEmpty(name)){
            return "缺少姓名";
        }
        if(StringUtils.isEmpty(time)){
            time = "09:00-18:00";
        }else{
            String[] times = time.split("-");
            if(times.length!=2){
                time = "09:00-18:00";
            }else {
                String[] time1s = times[0].split(":");
                String[] time2s = times[1].split(":");
                if(time1s.length!=2||time2s.length!=2){
                    time = "09:00-18:00";
                }else{
                    if(Integer.valueOf(time1s[0])<0||Integer.valueOf(time1s[0])>23||Integer.valueOf(time1s[1])<0||Integer.valueOf(time1s[1])>23){
                        time = "09:00-18:00";
                    }else if(Integer.valueOf(time2s[0])<0||Integer.valueOf(time2s[0])>23||Integer.valueOf(time2s[1])<0||Integer.valueOf(time2s[1])>23){
                        time = "09:00-18:00";
                    }
                }
            }
        }

        if(StringUtils.isEmpty(weekdays)){
            weekdays="1,2,3,4,5,6,7";
        }else{
            String[] days = weekdays.split(",");
            if(days.length>7){
                weekdays="1,2,3,4,5,6,7";
            }else{
                for(String s : days){
                    int dayInt = Integer.valueOf(s);
                    if(dayInt>7){
                        weekdays="1,2,3,4,5,6,7";
                        break;
                    }
                }
            }
        }




        String fileName = file.getOriginalFilename();

        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(!path.exists()) path = new File("");
        File upload = new File(path.getAbsolutePath(),"static\\head\\");
        if(!upload.exists())
            upload.mkdirs();
        System.out.println("upload url:"+upload.getAbsolutePath());
        String filePath=upload.getAbsolutePath();

        String[] splits = fileName.split(".");
        try {
            FileUtil.uploadFile(file.getBytes(), filePath, num+"."+splits[splits.length-1]);
        } catch (Exception e) {
            return "上传失败"+e.getMessage();
        }

        CustomService cs = csService.findByNum(num);
        if(cs==null){
            CustomService newCs = new CustomService();
            newCs.setName(name);
            newCs.setNum(num);
            newCs.setTime(time);
            newCs.setWeekday(weekdays);
            newCs.setPassword("123456");
            csService.SaveCS(newCs);
        }else {
            cs.setName(name);
            cs.setTime(time);
            cs.setWeekday(weekdays);
            csService.SaveCS(cs);

        }

        return "上传成功";
    }


    @ResponseBody
    @RequestMapping(value="/getPage", method = RequestMethod.GET)
    public Page<CustomService> getPage(int pageNum, int pageSize){
        return csService.findCSByPage(pageNum,pageSize);
    }

}
