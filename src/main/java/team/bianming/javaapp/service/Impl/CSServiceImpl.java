package team.bianming.javaapp.service.Impl;

import com.github.pagehelper.Page;
import team.bianming.javaapp.entity.CustomService;
import team.bianming.javaapp.entity.SessionInfo;
import team.bianming.javaapp.service.CSService;

import java.util.List;

/**
 * Created by xiaopeng on 2017/8/29.
 */
public class CSServiceImpl implements CSService {
    @Override
    public Page<CustomService> findCSByPage(int pageNo, int pageSize) {
        return null;
    }

    @Override
    public int DelteCS(CustomService customService) {
        return 0;
    }

    @Override
    public int SaveCS(CustomService customService) {
        return 0;
    }

    @Override
    public int UpdateCS(CustomService customService) {
        return 0;
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
