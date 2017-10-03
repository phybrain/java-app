package team.bianming.javaapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.bianming.javaapp.entity.Category;
import team.bianming.javaapp.mapper.CategoryMapper;
import team.bianming.javaapp.service.CategoryService;

import java.util.List;

/**
 * Created by xiaopeng on 2017/9/28.
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;
    @Override
    public List<Category> findAll() {

        return categoryMapper.selectAll();
    }

    @Override
    public int Add(Category category) {
        return categoryMapper.insert(category);
    }
}
