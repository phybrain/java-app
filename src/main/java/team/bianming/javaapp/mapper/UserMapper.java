package team.bianming.javaapp.mapper;

import org.apache.ibatis.annotations.*;
import team.bianming.javaapp.entity.User;

import java.util.List;

/**
 * Created by xiaopeng on 2017/8/29.
 */
@Mapper
public interface UserMapper {

    @Select("select * from user where id=#{id}")
    @Results({ @Result(id = true, column = "id", property = "id"),
               @Result(column = "name", property = "name"),
               @Result(column = "password", property = "password"),
               @Result(column = "idcard", property = "idcard"),
               @Result(column = "registerTime", property = "register_time")

            })
    public User selectById(int id) throws Exception;

    @Select("select * from user")
    @Results({ @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "password", property = "password"),
            @Result(column = "idcard", property = "idcard"),
            @Result(column = "registerTime", property = "register_time")
    })
    public List<User> selectAll() throws Exception;

    @Insert("insert into user (name,password,idcard,register_time) values (#{name},#{password},#{idcard},NOW())")
    @SelectKey(statement="select last_insert_id()",keyProperty="id", resultType=int.class, before=true)
    public int insert(User user) throws Exception;

    @Update("update user set name=#{name},password=#{password},idcard=#{idcard} where id=#{id}")
    public int update(User user) throws Exception;


    @Delete("delete from user where id=#{id}")
    public int delete(int id) throws Exception;


}
