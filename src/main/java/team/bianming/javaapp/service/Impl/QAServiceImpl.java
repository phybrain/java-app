package team.bianming.javaapp.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.bianming.javaapp.entity.QA;
import team.bianming.javaapp.mapper.QAMapper;
import team.bianming.javaapp.service.QAService;

/**
 * Created by xiaopeng on 2017/10/1.
 */
@Service
public class QAServiceImpl implements QAService {

    @Autowired
    QAMapper qaMapper;
    @Override
    public Page<QA> findQAByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return qaMapper.selectAll();

    }

    @Override
    public int DeleteQA(QA qa) {
        return qaMapper.delete(qa.getId()) ;
    }

    @Override
    public int SaveQA(QA qa) {
        return qaMapper.insert(qa);
    }

    @Override
    public int UpdateQA(QA qa) {
        return qaMapper.update(qa);
    }
}
