package com.example.guj_to_eng.Notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class createData extends SQLiteOpenHelper {
    public static final String databse_name="Note.db";  //DATABASE NAME
    public static final String table_name="Note"; //TABLE NAME
    public static final String col_1="ID";     //Increment ID
    public static final String col_2="DESCRIPTION"; // DESCRIPTION OR NOTE
    public static final String col_3="DATE"; //DATE
    public static final String col_4="TYPE"; //IMPORTANT OR NOTE
    SQLiteDatabase db=null; //DATABASE REFERENCE

    public createData(@Nullable Context context) {
        super(context,databse_name, null, 1);
        db=this.getWritableDatabase();  //GETTING DATABASE

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + table_name +" (ID INTEGER PRIMARY KEY AUTOINCREMENT ,DESCRIPTION TEXT ,DATE Text,TYPE Text );"); //CREATE TABLE
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void deleteDB(){
        db.execSQL("DROP TABLE IF EXISTS "+ table_name);  //DELETING TABLE
        onCreate(db);   //CREATING NEW DB
    }
    public boolean insertData(String d, String type){  //DESCRIPTION AND TYPE AS PARAMETER
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues c=new ContentValues(); //FOR INSERTING ROW
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String str=sdf.format(new Date()); //GETTING CURRENT DATE
        c.put(col_2,d);
        c.put(col_3,str);
        c.put(col_4,type);
        long result=db.insert(table_name,null,c);
        if (result==-1){
            return false;
        }
        else{
            return true;
        }
    }
    //GETDATA() IS FOR GETTING ALL ROW FROM TABLE
    public Cursor getData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+table_name,null);
        return res;
    }
    // GETDATA(String s) is for getting rows in which string s match
    public Cursor getData(String s){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+table_name +" where DESCRIPTION like "+"'%"+s+"%';",null);
        return res;
    }
    //GetDes(id) id for getting row of given id , it is used for on click perticular note
    public String getDes(int id){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor s=db.rawQuery("select * from " +table_name +" where ID="+id,null);
        while(s!=null&& s.moveToNext()){
            return s.getString(1)+"///"+s.getString(3);
        }
         return "";
    }
    // updating data
    public void updateData(int id, String des, String s){
        SQLiteDatabase db=this.getReadableDatabase();
        db.execSQL("UPDATE "+table_name+" SET  DESCRIPTION= "+"'"+des+"' "+" ,TYPE="+"'"+s+"' "+ "WHERE ID = "+"'"+id+"'");
    }
}