package team.bianming.javaapp.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import team.bianming.javaapp.entity.Category;
import team.bianming.javaapp.entity.User;

import java.util.List;

/**
 * Created by xiaopeng on 2017/9/28.
 */
@Repository
@Mapper
public interface CategoryMapper {

    @Select("select * from category ")
    @Results({ @Result(id = true, column = "id", property = "id"),
            @Result(column = "category_name", property = "categoryName"),



    })
    public List<Category> selectAll();

    @Insert("insert into category (category_name) values (#{categoryName})")
    @SelectKey(statement="select last_insert_id()",keyProperty="id", resultType=int.class, before=true)
    public int insert(Category category);


}
