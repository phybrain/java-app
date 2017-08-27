package team.bianming.javaapp.chenkeTest.mapperTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import team.bianming.javaapp.mapper.SessionMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ckwin8 on 2017/8/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SessionMapperTest {
    @Autowired
    private SessionMapper sessionMapper;

    @Test
    public void test1(){
        Map<String,Object> params = new HashMap<>();
        System.out.println(sessionMapper.getSessions(params).size());
    }
}
