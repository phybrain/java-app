package team.bianming.javaapp.entity;

import lombok.Data;

/**
 * Created by xiaopeng on 2017/9/14.
 */
@Data
public class News {

    private int id;
    private String content;
    private String title;
    private int categoryId;
    private String url;
}
