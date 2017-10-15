package team.bianming.javaapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.bianming.javaapp.entity.SessionInfo;
import team.bianming.javaapp.mapper.SessionMapper;
import team.bianming.javaapp.service.SessionService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by ckwin8 on 2017/8/28.
 */

@Service("SessionService")
public class SessionServiceImpl implements SessionService{
    @Autowired
    SessionMapper sessionMapper;
    @Override
    public List<SessionInfo> querySessions(Map<String, Object> params) {
        return sessionMapper.getSessions(params);
    }

    @Override
    public Integer createSession(SessionInfo sessionInfo) {
        return sessionMapper.createSession(sessionInfo);
    }

    @Override
    public void closeSession(Integer sessionId, Date date) {
        sessionMapper.closeSession(sessionId,date);
    }
}
