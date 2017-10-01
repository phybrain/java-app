package team.bianming.javaapp.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import team.bianming.javaapp.entity.News;
import team.bianming.javaapp.entity.User;

/**
 * Created by xiaopeng on 2017/9/14.
 */
@Repository
@Mapper
public interface NewsMapper {

    @Select("select * from news where id=#{id}")
    @Results({ @Result(id = true, column = "id", property = "id"),
            @Result(column = "title", property = "title"),
            @Result(column = "content", property = "content"),
            @Result(column = "category_id",property = "categoryId"),
            @Result(column = "url",property = "url")

    })
    public News selectById(int id);

    @Select("select * from news where category_id=#{categoryId}")
    @Results({ @Result(id = true, column = "id", property = "id"),
            @Result(column = "title", property = "title"),
            @Result(column = "content", property = "content"),
            @Result(column = "category_id",property = "categoryId"),
            @Result(column = "url",property = "url")

    })
    public Page<News> selectByCategoryId(int categoryId);

    @Insert("insert into news (title,content,category_id,url) values (#{title},#{content},#{categoryId},#{url})")
    @SelectKey(statement="select last_insert_id()",keyProperty="id", resultType=int.class, before=true)
    public int insert(News news);

    @Update("update news set content=#{content},title=#{title},url=#{url}, category_id=#{categoryId} where id=#{id}")
    public int update(News news) ;


    @Delete("delete from news where id=#{id}")
    public int delete(int id);


}
