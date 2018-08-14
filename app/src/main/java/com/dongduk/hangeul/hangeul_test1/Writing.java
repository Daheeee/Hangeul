package com.dongduk.hangeul.hangeul_test1;

import com.google.firebase.database.Exclude;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiyoungwon on 2017. 9. 25..
 */

public class Writing {

    private long rid;
    private String wid;
    private String uid;
    private String date;



    private String writing;


    public Writing() { }

    public Writing(String wid, String uid, String date, String writing) {
        this.wid = wid;
        this.uid = uid;
        this.date = date;
        this.writing = writing;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("wid", wid);
        result.put("date", date);
        result.put("writing", writing);


        return result;
    }

    public long getRid() {
        return rid;
    }

    public void setRid(long rid) {
        this.rid = rid;
    }

    public String getWid() {
        return wid;
    }

    public void setWid(String wid) {
        this.wid = wid;
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
