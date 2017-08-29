package team.bianming.javaapp.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by xiaopeng on 2017/8/29.
 */
@Data
public class User {

    private int id;
    private String name;
    private String idcard;
    private String  password;
    private Date registerTime;
}
