package com.dongduk.hangeul.hangeul_test1;

/**
 * Created by jiyoungwon on 2017. 9. 10..
 */

public class MyWord {

    private int _id;
    private String date;
    private String word;
    private String meaning;

    public MyWord() {
    }

    public MyWord(int _id, String date, String word, String meaning) {
        this._id = _id;
        this.date = date;
        this.word = word;
        this.meaning = meaning;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}
