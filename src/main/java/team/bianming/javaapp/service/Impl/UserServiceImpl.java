package team.bianming.javaapp.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.bianming.javaapp.entity.SessionInfo;
import team.bianming.javaapp.entity.User;
import team.bianming.javaapp.mapper.SessionMapper;
import team.bianming.javaapp.mapper.UserMapper;
import team.bianming.javaapp.service.UserService;

import java.util.List;

/**
 * Created by xiaopeng on 2017/8/29.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;
    @Autowired
    SessionMapper sessionMapper;

    @Override
    public Page<User> findUserByPage(int pageNum, int pageSize) {

        PageHelper.startPage(pageNum,pageSize);
        return userMapper.selectAll();
    }

    @Override
    public int DeleteUser(User user) {
        return userMapper.delete(user.getId());
    }

    @Override
    public int SaveUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int UpdateUser(User user) {
        return userMapper.update(user);
    }

    @Override
    public List<SessionInfo> findSessionInfoByUser(User user) {
        return null;
    }

    @Override
    public SessionInfo findLastSessionInfo(User user) {
        return sessionMapper.getUserLastSession(user.getId());
    }
}
