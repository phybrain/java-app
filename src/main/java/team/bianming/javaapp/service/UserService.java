package team.bianming.javaapp.service;

import com.github.pagehelper.Page;
import org.springframework.stereotype.Service;
import team.bianming.javaapp.entity.SessionInfo;
import team.bianming.javaapp.entity.User;

import java.util.List;

/**
 * Created by xiaopeng on 2017/8/29.
 */

public interface UserService {

    public Page<User> findUserByPage(int pageNum, int pageSize);
    public int DeleteUser(User user);
    public int SaveUser (User user);
    public int UpdateUser(User user);
    public List<SessionInfo> findSessionInfoByUser(User user);
    public SessionInfo findLastSessionInfo(User user);
    public User login(String name,String password);
}
