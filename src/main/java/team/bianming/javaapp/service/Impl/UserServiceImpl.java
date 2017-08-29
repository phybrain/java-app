package team.bianming.javaapp.service.Impl;

import com.github.pagehelper.Page;
import org.springframework.stereotype.Service;
import team.bianming.javaapp.entity.SessionInfo;
import team.bianming.javaapp.entity.User;
import team.bianming.javaapp.service.UserService;

import java.util.List;

/**
 * Created by xiaopeng on 2017/8/29.
 */
@Service
public class UserServiceImpl implements UserService{


    @Override
    public Page<User> findUserByPage(int pageNo, int pageSize) {
        return null;
    }

    @Override
    public int DelteUser(User user) {
        return 0;
    }

    @Override
    public int SaveUser(User user) {
        return 0;
    }

    @Override
    public int UpdateUser(User user) {
        return 0;
    }

    @Override
    public List<SessionInfo> findSessionInfoByUser(User user) {
        return null;
    }

    @Override
    public SessionInfo findLastSessionInfo(User user) {
        return null;
    }
}
