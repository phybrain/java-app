package team.bianming.javaapp.service;

import com.github.pagehelper.Page;
import team.bianming.javaapp.entity.CustomService;
import team.bianming.javaapp.entity.SessionInfo;
import team.bianming.javaapp.entity.User;

import java.util.List;

/**
 * Created by xiaopeng on 2017/8/29.
 */
public interface CSService {

    public Page<CustomService> findCSByPage(int pageNum, int pageSize);
    public int DeleteCS(CustomService customService);
    public int SaveCS (CustomService customService);
    public int UpdateCS(CustomService customService);
    public List<SessionInfo> findSessionInfoByCS(CustomService customService);
    public List<SessionInfo> findRecentSessionInfo( int Size,CustomService customService);
    public CustomService findByNum(String num);
    public CustomService findByNamePass(String name,String password);
}
