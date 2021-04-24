package com.example.guj_to_eng;

public class word {

    private String mEngTranslation;
    private String mGujTranslation;
    private int mImageResourceId= no_image;
    private int mAudioResourceId ;

    private static final int no_image=-1;

    public word(String gujTranslation, String engTranslation,int maudio) {
        mEngTranslation = engTranslation;
        mGujTranslation = gujTranslation;
        mAudioResourceId= maudio;

    }
    public word(String gujTranslation, String engTranslation,int mimage,int maudio) {
        mEngTranslation = engTranslation;
        mGujTranslation = gujTranslation;
        mImageResourceId=  mimage;
        mAudioResourceId= maudio;

    }


    public String getEngTranslation() {
        return mEngTranslation;
    }

    public String getGujTranslation() {
        return mGujTranslation;
    }
    public int getmImageResourceId(){return mImageResourceId;}

    public int getmAudioResourceId() { return mAudioResourceId; }

    public boolean hasImage(){return mImageResourceId!=no_image;}
}
