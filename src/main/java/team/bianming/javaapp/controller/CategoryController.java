package team.bianming.javaapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import team.bianming.javaapp.entity.Category;
import team.bianming.javaapp.service.CategoryService;

import java.util.List;

/**
 * Created by xiaopeng on 2017/9/28.
 */
@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @ResponseBody
    @RequestMapping("/categoryList")
    public List<Category> findAllCategory(){

        return categoryService.findAll();
    }

    @ResponseBody
    @RequestMapping("/addCategory")
    public int add(Category category){

        return categoryService.Add(category);
    }

}
