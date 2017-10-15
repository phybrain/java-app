package team.bianming.javaapp.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import team.bianming.javaapp.entity.QA;

/**
 * Created by xiaopeng on 2017/10/1.
 */
@Repository
@Mapper
public interface QAMapper {

    @Select("select * from qa where question like CONCAT(CONCAT('%', #{s}), '%') or answer like CONCAT(CONCAT('%', #{s}), '%')")
    @Results({@Result(id = true, column = "id", property = "id"),
            @Result(column = "question", property = "question"),
            @Result(column = "answer", property = "answer"),
    })
    public Page<QA> selectAllbyS(String s);

    @Select("select * from qa where id=#{id}")
    @Results({ @Result(id = true, column = "id", property = "id"),
            @Result(column = "question", property = "question"),
            @Result(column = "answer", property = "answer"),

    })
    public QA selectById(int id);

    @Select("select * from qa")
    @Results({@Result(id = true, column = "id", property = "id"),
            @Result(column = "question", property = "question"),
            @Result(column = "answer", property = "answer"),
    })
    public Page<QA> selectAll();

    @Insert("insert into qa (question,answer) values (#{question},#{answer})")
    @SelectKey(statement="select last_insert_id()",keyProperty="id", resultType=int.class, before=true)
    public int insert(QA qa);

    @Update("update qa set question=#{question},answer=#{answer}where id=#{id}")
    public int update(QA qa) ;


    @Delete("delete from qa where id=#{id}")
    public int delete(int id);
}
