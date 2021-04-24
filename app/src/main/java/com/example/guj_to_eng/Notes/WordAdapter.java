package com.example.guj_to_eng.Notes;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.guj_to_eng.R;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {
    private int colorResourceId;
    public WordAdapter(@NonNull Context context, ArrayList<Word> androidFlavors, int color) {
        super(context, 0,androidFlavors);
        colorResourceId=color;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item1, parent, false);
        }

        Word currentWord = getItem(position);

        TextView date = (TextView) listItemView.findViewById(R.id.date1);  //date
        date.setText(currentWord.getDate());

        TextView type = (TextView) listItemView.findViewById(R.id.impo);// important
        type.setText("•");
        if(currentWord.getType().equals("IMP"))
        type.setTextColor(Color.rgb(200,0,0));


        TextView description = (TextView) listItemView.findViewById(R.id.descr); //description or note
        String s=currentWord.getDes();
        String ans="";
        int n=s.length();
        if(n>25)ans=s.substring(0,25);// only 25 character shows as hint
        else ans=s;
        description.setText(ans);

        return listItemView;
    }
}
