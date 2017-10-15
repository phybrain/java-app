package team.bianming.javaapp.service;

import team.bianming.javaapp.entity.RecordInfo;
import team.bianming.javaapp.entity.SessionInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by ckwin8 on 2017/8/28.
 */
public interface RecordService {
    List<RecordInfo> queryRecords(Map<String,Object> params);

    void addRecord(RecordInfo recordInfo);
}
