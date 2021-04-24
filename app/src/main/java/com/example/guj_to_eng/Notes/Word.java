package com.example.guj_to_eng.Notes;
public class Word {

    private String mdes;
    private String mdate;
    private String mtype ;

    private static final int no_image=-1;

    public Word(String des, String date, String type) {
        mdes = des;
        mdate = date;
        mtype= type;

    }


    public String getDes() {
        return mdes;
    }

    public String getDate() {
        return mdate;
    }
    public String getType(){
        return mtype;
    }


}
