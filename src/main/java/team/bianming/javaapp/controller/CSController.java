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
import team.bianming.javaapp.entity.*;
import team.bianming.javaapp.service.CSService;
import team.bianming.javaapp.service.RecordService;
import team.bianming.javaapp.service.SessionService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by ckwin8 on 2017/10/1.
 */

@Controller
@RequestMapping("/cs")
public class CSController {

    private static final Map<Integer,TalkPool> sessionPool = new Hashtable<>();//sessionId==>queue
    private static final Map<Integer,Integer> nowSessions = new Hashtable<>();//csId==>sessionId
    private static final Map<Integer,Integer> cps1 = new Hashtable<>();//cp:csId==>userId
    private static final Map<Integer,Integer> cps2 = new Hashtable<>();//cp:userId==>csId

    private static final Set<Integer> nowInCS = new HashSet<>();//当前在线的cs
    private static final Set<Integer> nowEmptyCS = new HashSet<>();//当前空闲的cs

    @Autowired
    CSService csService;

    @Autowired
    SessionService sessionService;

    @Autowired
    RecordService recordService;

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

        String[] splits = fileName.split("\\.");
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
    @RequestMapping(value="/getPage", method = {RequestMethod.GET,RequestMethod.POST})
    public Page<CustomService> getPage(int pageNum, int pageSize){
        return csService.findCSByPage(pageNum,pageSize);
    }

/////////////////////////////////////////////////////////////////////////////////
    //////session 里存的是type,csid,userid

    @ResponseBody
    @RequestMapping(value = "/createNewSession",method = RequestMethod.GET)
    public Result createSession(HttpSession session){//用户申请客服对话
        Object[] nowEmptyCSArr = nowEmptyCS.toArray();
        Random random = new Random();


        Result result = new Result();
        if(nowEmptyCSArr.length==0){
            result.setCode("false");
            return result;
        }

        int randInt = random.nextInt(nowEmptyCSArr.length);
        Integer csId = (Integer) nowEmptyCSArr[randInt];
        Integer uId = (Integer) session.getAttribute("userid");
        nowEmptyCS.remove(csId);
        SessionInfo sessionInfo = new SessionInfo();
        sessionInfo.setStarttime(new Date());
        sessionInfo.setCsId(csId);
        sessionInfo.setUserId(uId);

        sessionService.createSession(sessionInfo);
        nowSessions.put(csId,sessionInfo.getId());
        cps1.put(csId,uId);
        cps2.put(uId,csId);
        sessionPool.put(sessionInfo.getId(),new TalkPool());

        result.setCode("true");
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(HttpSession httpSession, String name, String password){//客服登录
        CustomService cs =csService.findByNamePass(name,password);
        if(cs==null){
            return "false";
        }else{
           httpSession.setAttribute("csid",cs.getId());
           httpSession.setAttribute("type","cs");
           nowEmptyCS.add(cs.getId());
           return "true";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getNewRecords",method = {RequestMethod.POST,RequestMethod.GET})
    public List<RecordInfo> getNewRecords(HttpSession httpSession){//轮询时调用,返回null表示对话已经结束了
        Integer sessionId = null;
        if("cs".equals(httpSession.getAttribute("type"))){//是客服
            sessionId = nowSessions.get(httpSession.getAttribute("csid"));

            TalkPool talkPool = sessionPool.get(sessionId);
            List<RecordInfo> recordInfos = new ArrayList<>();
            Queue<RecordInfo> queue = talkPool.getUser2cs();
            while (!queue.isEmpty()){
                recordInfos.add(queue.poll());
            }
            return recordInfos;
        }else if("user".equals(httpSession.getAttribute("type"))){//是用户
            Integer csid = cps2.get(httpSession.getAttribute("userid"));
            sessionId = nowSessions.get(csid);

            TalkPool talkPool = sessionPool.get(sessionId);
            List<RecordInfo> recordInfos = new ArrayList<>();
            Queue<RecordInfo> queue = talkPool.getCs2user();
            while (!queue.isEmpty()){
                recordInfos.add(queue.poll());
            }
            return recordInfos;
        }

        List<RecordInfo> res = new ArrayList<>();
        RecordInfo recordInfo = new RecordInfo();
        recordInfo.setContent("xxee");
        recordInfo.setSendtime(new Date());
        recordInfo.setSenderType("cs");
        recordInfo.setSenderId(1);
        recordInfo.setAccepterId(1);
        recordInfo.setId(1);
        recordInfo.setSessionId(1);
        res.add(recordInfo);
        return res;
    }


    @ResponseBody
    @RequestMapping(value = "/endSession",method = {RequestMethod.POST,RequestMethod.GET})
    public Result endSession(HttpSession session){//结束会话
        if("cs".equals(session.getAttribute("type"))){
            Integer csid = (Integer) session.getAttribute("csid");
            Integer sessionId = nowSessions.get(csid);
            Integer userid = cps1.get(csid);

            sessionService.closeSession(sessionId,new Date());

            sessionPool.remove(sessionId);
            nowSessions.remove(csid);
            cps1.remove(csid);
            cps2.remove(userid);
            nowEmptyCS.add(csid);
        }else if("user".equals(session.getAttribute("type"))){
            Integer userid = (Integer) session.getAttribute("userid");
            Integer csid = cps2.get(userid);
            Integer sessionId = nowSessions.get(csid);

            sessionService.closeSession(sessionId,new Date());

            sessionPool.remove(sessionId);
            nowSessions.remove(csid);
            cps1.remove(csid);
            cps2.remove(userid);
            nowEmptyCS.add(csid);
        }

        Result result = new Result();
        result.setCode("true");
        return result;
    }


    @ResponseBody
    @RequestMapping(value = "/send",method = {RequestMethod.POST,RequestMethod.GET})
    public Result sendRecord(String content,HttpSession session){//若对话已经结束，返回的code为false
        Result result = new Result();

        if("cs".equals(session.getAttribute("type"))){
            int csid = (Integer) session.getAttribute("csid");

            if(!cps1.containsKey(csid)){
                result.setCode("false");
                return result;
            }

            int userid = cps1.get(csid);
            Integer sessionId = nowSessions.get(csid);

            TalkPool talkPool = sessionPool.get(sessionId);
            Queue<RecordInfo> queue = talkPool.getCs2user();

            RecordInfo recordInfo = new RecordInfo();
            recordInfo.setSenderId(csid);
            recordInfo.setSenderType("cs");
            recordInfo.setSendtime(new Date());
            recordInfo.setAccepterId(userid);
            recordInfo.setSessionId(sessionId);
            recordInfo.setContent(content);

            queue.add(recordInfo);
            recordService.addRecord(recordInfo);
        }else if("user".equals(session.getAttribute("type"))){
            Integer userid = (Integer) session.getAttribute("userid");

            if(!cps2.containsKey(userid)){
                result.setCode("false");
                return result;
            }

            Integer csid = cps2.get(userid);
            Integer sessionId = nowSessions.get(csid);
            TalkPool talkPool = sessionPool.get(sessionId);
            Queue<RecordInfo> queue = talkPool.getUser2cs();

            RecordInfo recordInfo = new RecordInfo();
            recordInfo.setSenderId(csid);
            recordInfo.setSenderType("cs");
            recordInfo.setSendtime(new Date());
            recordInfo.setAccepterId(userid);
            recordInfo.setSessionId(sessionId);
            recordInfo.setContent(content);

            queue.add(recordInfo);
            recordService.addRecord(recordInfo);

        }

        result.setCode("true");
        return result;
    }


    @ResponseBody
    @RequestMapping(value = "/xunconnect",method = {RequestMethod.POST,RequestMethod.GET})
    public String  xunconnect(HttpSession session){
        Integer csid = (Integer) session.getAttribute("csid");
        if(csid==null){
            return "false";
        }
        if(cps2.containsKey(csid)){//有会话
            return "true";
        }
        return "false";

    }

}
