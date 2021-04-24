package com.example.guj_to_eng.Notes;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.guj_to_eng.R;

public class AddNote extends AppCompatActivity {
    static createData my_db;
    EditText e;
    Button add,ret;
    RadioButton imp;
    int Id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        Id = getIntent().getIntExtra("ID",-1); //getting extra value pass with activity call as ID
        my_db=Note.my_db;
        e=(EditText)findViewById(R.id.des);
        add=(Button)findViewById(R.id.add);

        imp=(RadioButton)findViewById(R.id.imp);
        if(Id!=-1) { // if id is not null means addNote activity call for showing already present note
            String s = my_db.getDes(Id); // get data for given id
            String k[]=s.split("///");
            e.setText(k[0]);    //set text of note
            if(k[1].equals("IMP")){
                imp.setChecked(true);
            }
            else
                imp.setChecked(false);
        }
        //add note is for adding or updating note
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s="NOT";
                if(imp.isChecked()) s="IMP";
                if(Id==-1) {  //if new note is inserted
                    boolean result = my_db.insertData(e.getText().toString(), s);
                    if (result == true) {
                        Toast.makeText(AddNote.this, "Note inserted", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(AddNote.this, "Note not inserted", Toast.LENGTH_SHORT).show();
                }
                else{ //else update
                   my_db.updateData(Id,e.getText().toString(),s);
                }
            }
        });

    }
}