package team.bianming.javaapp.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by ckwin8 on 2017/8/27.
 */
@Data
public class SessionInfo {
    private int id;
    private Date starttime;
    private Date endtime;
    private int userId;
    private int csId;
}
