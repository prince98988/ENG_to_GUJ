package com.example.guj_to_eng;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<word> {
private int colorResourceId;
    public WordAdapter(@NonNull Context context, ArrayList<word> androidFlavors,int color) {
        super(context, 0,androidFlavors);
        colorResourceId=color;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        word currentWord = getItem(position);

        TextView GujTextView = (TextView) listItemView.findViewById(R.id.guj_text_view);

        GujTextView.setText(currentWord.getGujTranslation());

        TextView EngTextView = (TextView) listItemView.findViewById(R.id.eng_text_view);

        EngTextView.setText(currentWord.getEngTranslation());

        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
       ImageView iconView = (ImageView) listItemView.findViewById(R.id.image_view);
        // Get the image resource ID from the current AndroidFlavor object and
        // set the image to iconView
        if (currentWord.hasImage()==true){
        iconView.setImageResource(currentWord.getmImageResourceId());
        iconView.setVisibility(View.VISIBLE);}
        else{
            iconView.setVisibility(View.GONE);
        }
        View text=listItemView.findViewById(R.id.text_container);
        int color_text= ContextCompat.getColor(getContext(),colorResourceId);
        text.setBackgroundColor(color_text);


        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView

        return listItemView;
    }
}
