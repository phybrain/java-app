package team.bianming.javaapp.mapper;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;
import team.bianming.javaapp.entity.RecordInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by ckwin8 on 2017/8/28.
 */
@Repository
public interface RecordMapper {
    List<RecordInfo> getRecords(Map<String,Object> params);

    @Insert("insert into record (session_id,sender_type,sendtime,accepter_id,sender_id,content) values (#{sessionId},#{senderType},#{sendtime},#{accepterId},#{senderId},#{content})")
    void insertRecord(RecordInfo recordInfo);
}
