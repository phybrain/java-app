package team.bianming.javaapp.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.bianming.javaapp.entity.News;
import team.bianming.javaapp.mapper.NewsMapper;
import team.bianming.javaapp.service.NewsService;

/**
 * Created by xiaopeng on 2017/9/14.
 */
@Service
public class NewsServiceImpl implements NewsService{


    @Autowired
    NewsMapper newsMapper;

    @Override
    public Page<News> findNewsByCategory(int pageNum, int pageSize, int categoryId) {
        PageHelper.startPage(pageNum,pageSize);
        return newsMapper.selectByCategoryId(categoryId);
    }

    @Override
    public int DeleteNews(News news) {
        return newsMapper.delete(news.getId());
    }

    @Override
    public int SaveNews(News news) {
        return newsMapper.insert(news);
    }

    @Override
    public int UpdateNews(News news) {
        return newsMapper.update(news);
    }
}
