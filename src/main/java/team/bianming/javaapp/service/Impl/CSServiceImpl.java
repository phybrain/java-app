package team.bianming.javaapp.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import team.bianming.javaapp.entity.CustomService;
import team.bianming.javaapp.entity.SessionInfo;
import team.bianming.javaapp.mapper.CustomServiceMapper;
import team.bianming.javaapp.service.CSService;

import java.util.List;

/**
 * Created by xiaopeng on 2017/8/29.
 */
public class CSServiceImpl implements CSService {
    
    @Autowired
    CustomServiceMapper customServiceMapper;

    @Override
    public Page<CustomService> findCSByPage(int pageNum, int pageSize) {

        PageHelper.startPage(pageNum,pageSize);
        return customServiceMapper.selectAll();
    }

    @Override
    public int DelteCS(CustomService customService) {
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
        return null;
    }
}
