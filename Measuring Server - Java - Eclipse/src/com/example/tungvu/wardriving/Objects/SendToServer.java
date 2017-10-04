package com.example.tungvu.wardriving.Objects;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by tung on 3/9/17.
 */

public class SendToServer implements Serializable{
    ArrayList<Sample> list;
    private float x;
    private float y;
    private boolean isSurvey;
    public SendToServer(ArrayList<Sample> list, float x, float y, boolean isSurvey){
        this.x = x;
        this.y = y;
        this.isSurvey = isSurvey;
        this.list = list;
    }
    public ArrayList<Sample> getListSample(){
        return list;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public boolean isSurvey() {
        return isSurvey;
    }
}
