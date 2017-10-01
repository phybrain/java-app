package team.bianming.javaapp.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import team.bianming.javaapp.entity.User;

import java.util.List;

/**
 * Created by xiaopeng on 2017/8/29.
 */
@Repository
@Mapper
public interface UserMapper {

    @Select("select * from user where id=#{id}")
    @Results({ @Result(id = true, column = "id", property = "id"),
               @Result(column = "name", property = "name"),
               @Result(column = "password", property = "password"),
               @Result(column = "idcard", property = "idcard"),
               @Result(column = "registerTime", property = "register_time")

            })
    public User selectById(int id);

    @Select("select * from user")
    @Results({ @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "password", property = "password"),
            @Result(column = "idcard", property = "idcard"),
            @Result(column = "registerTime", property = "register_time")
    })
    public Page<User> selectAll();

    @Insert("insert into user (name,password,idcard,register_time) values (#{name},#{password},#{idcard},NOW())")
    @SelectKey(statement="select last_insert_id()",keyProperty="id", resultType=int.class, before=true)
    public int insert(User user);

    @Update("update user set name=#{name},password=#{password},idcard=#{idcard} where id=#{id}")
    public int update(User user) ;


    @Delete("delete from user where id=#{id}")
    public int delete(int id);


}
