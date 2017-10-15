package team.bianming.javaapp.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by ckwin8 on 2017/8/28.
 */
@Data
public class RecordInfo {
    int id;
    int sessionId;
    String senderType;
    int senderId;
    int accepterId;
    Date sendtime;
    String content;
}
