package com.caocao.bestmarket.notification;

/**
 * Created by vutuananh on 4/13/2018.
 */

public class Note {
    private int mImage=-1;
    private String mContent;
    private String mTime;
    public Note(int picture, String contentString , String timeString){
        mImage =picture;
        mContent= contentString;
        mTime = timeString;
    }

    public int getImage(){
        return mImage;
    }

    public String getContent(){
        return mContent;
    }

    public String getTime(){
        return mTime;
    }

    public boolean hasImage(){
        return mImage != -1;
    }
}
