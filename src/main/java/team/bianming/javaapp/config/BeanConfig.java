package team.bianming.javaapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by ckwin8 on 2017/10/16.
 */

@Configuration
public class BeanConfig {

    @Bean
    public RestTemplate sss(){
        return new RestTemplate();
    }

}
