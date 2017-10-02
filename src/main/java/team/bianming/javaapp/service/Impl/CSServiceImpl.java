package team.bianming.javaapp.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.bianming.javaapp.entity.CustomService;
import team.bianming.javaapp.entity.SessionInfo;
import team.bianming.javaapp.mapper.CustomServiceMapper;
import team.bianming.javaapp.mapper.SessionMapper;
import team.bianming.javaapp.service.CSService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaopeng on 2017/8/29.
 */

@Service("CSService")
public class CSServiceImpl implements CSService {

    @Autowired
    CustomServiceMapper customServiceMapper;
    @Autowired
    SessionMapper sessionMapper;

    @Override
    public Page<CustomService> findCSByPage(int pageNum, int pageSize) {


        PageHelper.startPage(pageNum,pageSize);
        return customServiceMapper.selectAll();
    }

    @Override
    public int DeleteCS(CustomService customService) {
        return customServiceMapper.delete(customService.getId());
    }

    @Override
    public int SaveCS(CustomService customService) {
        return customServiceMapper.insert(customService);
    }

    @Override
    public int UpdateCS(CustomService customService) {
        return customServiceMapper.update(customService);
    }

    @Override
    public List<SessionInfo> findSessionInfoByCS(CustomService customService) {
        return null;
    }

    @Override
    public List<SessionInfo> findRecentSessionInfo(int Size, CustomService customService) {
        Map<String,Object> map=new HashMap<>();
        map.put("pageSize",Size);
        map.put("csId",customService.getId());
        return sessionMapper.getCSRecentSessions(map);
    }

    @Override
    public CustomService findByNum(String num) {
        return customServiceMapper.getByNum(num);
    }
}
