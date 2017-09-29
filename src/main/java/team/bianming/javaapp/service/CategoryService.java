package team.bianming.javaapp.service;

import team.bianming.javaapp.entity.Category;

import java.util.List;

/**
 * Created by xiaopeng on 2017/9/28.
 */
public interface CategoryService {

    public List<Category> findAll();

    public int Add(Category category);
}
