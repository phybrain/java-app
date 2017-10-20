package team.bianming.javaapp.entity;

import lombok.Data;

/**
 * Created by ckwin8 on 2017/10/18.
 */
//@Data
public class AiAnswerVo {
    boolean isRight=false;
    String answer;

    public String getAnswer() {
        return answer;
    }

    public boolean getIsRight() {
        return isRight;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setIsRight(boolean right) {
        isRight = right;
    }
}
