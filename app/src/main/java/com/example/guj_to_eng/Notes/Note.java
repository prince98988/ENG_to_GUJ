package com.example.guj_to_eng.Notes;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.guj_to_eng.R;

import java.util.ArrayList;

public class Note extends AppCompatActivity {
    static  createData my_db;
    Button addNote,clearNote,refresh;
    ListView listview = null;
    SearchView search=null;
    ArrayList<Word> words=new ArrayList<>();
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        listview=(ListView)findViewById(R.id.list);
        addNote = (Button) findViewById(R.id.addNote);
        clearNote = (Button) findViewById(R.id.clearNote);
        refresh = (Button) findViewById(R.id.refresh);
        search=(SearchView)findViewById(R.id.search);
        my_db=new createData(Note.this);
        Cursor res=null;
        if(my_db!=null){
            res=my_db.getData();}
        onmake(res);
        //addNote button is to call addNote activity
        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent noteIntent = new Intent(Note.this, AddNote.class);
                startActivity(noteIntent);
            }
        });
        //clearNote button is for clearing all note
        clearNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                my_db.deleteDB();
                my_db=new createData(Note.this);
                Cursor res=null;
                if(my_db!=null){
                    res=my_db.getData();}
                onmake(res);
            }
        });
        //refresh for refreshing activity
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                my_db=new createData(Note.this);
                Cursor res=null;
                if(my_db!=null){
                    res=my_db.getData(); //getting all notes from database
                }
                onmake(res);
            }
        });
        //search button for searching notes
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                my_db=new createData(Note.this);
                Cursor res=null;
                if(my_db!=null){
                    res=my_db.getData(s);  //getting data for given hints of notes in String s
                }
                onmake(res);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
       search.setOnCloseListener(new SearchView.OnCloseListener() {
           @Override
           public boolean onClose() {
               my_db=new createData(Note.this);
               Cursor res=null;
               if(my_db!=null){
                   res=my_db.getData(); //onclose search it again shows all notes
               }
               onmake(res);
               return false;
           }
       });
        }
        protected void onmake(Cursor res)
      {
           words.clear();
            while(res!=null&&res.moveToNext()){
                words.add(new Word(res.getString(1),res.getString(2),res.getString(3))); // add details in arraylist
            }
           if(words.size()==0){

               Toast.makeText(this,"NO DATA", Toast.LENGTH_SHORT).show();
           }
            listview.setAdapter(new WordAdapter(this,words,R.color.colorAccent));
           listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  Intent noteIntent = new Intent(Note.this, AddNote.class);   // AddNote.class is for adding new notes and updating notes
                  noteIntent.putExtra("ID", position+1);  //add position of selected note
                  startActivity(noteIntent);
                  finish();

              }
          });
        }
    }