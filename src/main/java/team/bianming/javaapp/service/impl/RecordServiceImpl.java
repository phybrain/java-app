package team.bianming.javaapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.bianming.javaapp.entity.RecordInfo;
import team.bianming.javaapp.mapper.RecordMapper;
import team.bianming.javaapp.service.RecordService;

import java.util.List;
import java.util.Map;

/**
 * Created by ckwin8 on 2017/8/28.
 */

@Service("RecordService")
public class RecordServiceImpl implements RecordService{
    @Autowired
    RecordMapper recordMapper;

    @Override
    public List<RecordInfo> queryRecords(Map<String, Object> params) {
        return recordMapper.getRecords(params);
    }

    @Override
    public void addRecord(RecordInfo recordInfo) {
        recordMapper.insertRecord(recordInfo);
    }


}
