package team.bianming.javaapp.entity;

import lombok.Data;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by ckwin8 on 2017/10/15.
 */
@Data
public class TalkPool {

    Queue<RecordInfo> cs2user;
    Queue<RecordInfo> user2cs;

    public TalkPool(){
        cs2user = new ConcurrentLinkedQueue<>();
        user2cs = new ConcurrentLinkedQueue<>();
    }

}
