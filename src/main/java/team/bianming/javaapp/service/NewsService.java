package team.bianming.javaapp.service;

import com.github.pagehelper.Page;
import team.bianming.javaapp.entity.News;
import team.bianming.javaapp.entity.SessionInfo;
import team.bianming.javaapp.entity.User;

import java.util.List;

/**
 * Created by xiaopeng on 2017/9/14.
 */
public interface NewsService {

    public Page<News> findNewsByCategory(int pageNum, int pageSize,int categoryId);
    public int DeleteNews(News news);
    public int SaveNews (News news);
    public int UpdateNews(News news);
}
