package team.bianming.javaapp.service;

import team.bianming.javaapp.entity.SessionInfo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by ckwin8 on 2017/8/28.
 */
public interface SessionService {
    List<SessionInfo> querySessions(Map<String,Object> params);

    Integer createSession(SessionInfo sessionInfo);

    void closeSession(Integer sessionId,Date date);
}
