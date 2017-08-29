package team.bianming.javaapp.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;
import team.bianming.javaapp.entity.CustomService;
import team.bianming.javaapp.entity.User;

import java.util.List;

/**
 * Created by xiaopeng on 2017/8/28.
 */
@Mapper
public interface CustomServiceMapper {


    @Select("select * from custom_service where id=#{id}")
    @Results({ @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "password", property = "password"),
            @Result(column = "time", property = "time"),
            @Result(column = "weekday", property = "weekday"),
            @Result(column = "num", property = "num"),

    })
    public CustomService selectById(int id);

    @Select("select * from custom_service")
    @Results({ @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "password", property = "password"),
            @Result(column = "time", property = "time"),
            @Result(column = "weekday", property = "weekday"),
            @Result(column = "num", property = "num"),

    })
    public Page<CustomService> selectAll() ;


    @Insert("insert into custom_service (name,password,weekday,time,num) values (#{name},#{password},#{weekday},#{time},#{num})")
    @SelectKey(statement="select last_insert_id()",keyProperty="id", resultType=int.class, before=true)
    public int insert(CustomService customService);

    @Update("update custom_service set name=#{name},password=#{password},weekday=#{weekday},num=#{num},time=#{time} where id=#{id}")
    public int update(CustomService customService);


    @Delete("delete from custom_service where id=#{id}")
    public int delete(int id);
}
