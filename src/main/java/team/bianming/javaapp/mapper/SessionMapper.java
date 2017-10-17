package team.bianming.javaapp.mapper;

import com.sun.javafx.collections.MappingChange;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import team.bianming.javaapp.entity.SessionInfo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by ckwin8 on 2017/8/27.
 */
@Repository
public interface SessionMapper {
    List<SessionInfo> getSessions(Map<String,Object> params);

    /**
     * 获取用户上次的对话
     * @param userId
     * @return
     */
    SessionInfo getUserLastSession(int userId);

    /**
     * 获取客服最近的n条对话
     * @param params pageSize csId
     * @return
     */
    List<SessionInfo> getCSRecentSessions(Map<String,Object> params);


    @Insert("insert into session (starttime,user_id,cs_id) values(#{starttime},#{userId},#{csId})")
    @SelectKey(statement="select last_insert_id()",keyProperty="id", resultType=int.class, before=true)
    Integer createSession(SessionInfo sessionInfo);

    @Update("update session set endtime=#{date} where id=#{id}")
    void closeSession(@Param("id") Integer id, @Param("date") Date date);
}
