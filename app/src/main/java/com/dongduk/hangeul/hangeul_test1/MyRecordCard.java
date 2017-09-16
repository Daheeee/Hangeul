package com.dongduk.hangeul.hangeul_test1;

/**
 * Created by yoon1 on 2017-09-17.
 */

public class MyRecordCard {
    String tvDateRecord;
    String tvWord1;

    public String getTvDateRecord() {
        return tvDateRecord;
    }

    public String getTvWord1() {
        return tvWord1;
    }

    public void setTvDateRecord(String tvDate) {
        this.tvDateRecord = tvDateRecord;
    }

    public void setTvWord1(String tvWord1) {
        this.tvWord1 = tvWord1;
    }

    public MyRecordCard(String tvDateRecord, String tvWord1) {
        this.tvDateRecord = tvDateRecord;
        this.tvWord1 = tvWord1;
    }
}
