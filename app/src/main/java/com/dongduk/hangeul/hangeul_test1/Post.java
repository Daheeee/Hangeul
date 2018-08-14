package com.dongduk.hangeul.hangeul_test1;

import com.google.firebase.database.Exclude;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiyoungwon on 2017. 9. 25..
 */

public class Post {

    private long postid;
    private String wordid;
    private String uid;
    private String date;
    private String title;
    private String writing;
    private String ex;


    public Post() { }


    public Post(String wordid, String uid, String date, String title, String writing) {
        this.wordid = wordid;
        this.uid = uid;
        this.date = date;
        this.title = title;
        this.writing = writing;
    }



    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("wid", wordid);
        result.put("date", date);
        result.put("writing", writing);


        return result;
    }

    public long getRid() {
        return postid;
    }

    public void setRid(long rid) {
        this.postid = rid;
    }

    public String getWid() {
        return wordid;
    }

    public void setWid(String wid) {
        this.wordid = wid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWriting() {
        return writing;
    }

    public void setWriting(String writing) {
        this.writing = writing;
    }


}
