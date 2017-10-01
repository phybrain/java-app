package team.bianming.javaapp.service;

import com.github.pagehelper.Page;
import team.bianming.javaapp.entity.QA;

/**
 * Created by xiaopeng on 2017/10/1.
 */
public interface QAService {

    public Page<QA> findQAByPage(int pageNum, int pageSize);
    public int DeleteQA(QA qa);
    public int SaveQA (QA qa);
    public int UpdateQA(QA qa);
}
