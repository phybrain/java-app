package team.bianming.javaapp.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.springframework.stereotype.Repository;
import team.bianming.javaapp.entity.SessionInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by ckwin8 on 2017/8/27.
 */
@Repository
public interface SessionMapper {
    List<SessionInfo> getSessions(Map<String,Object> params);
}
