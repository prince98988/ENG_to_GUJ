package com.example.guj_to_eng;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.guj_to_eng.Notes.Note;

public class MainActivity extends AppCompatActivity {
    private AudioManager mAudioManager;
    private MediaPlayer mMediaPlayer;

    private MediaPlayer.OnCompletionListener mCompletion =new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mp) { //onCompletion of song it release Media player
            releaseMediaPlayer();
        }
    };

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if ((focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) && mMediaPlayer != null) {
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            }
            else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAudioManager=(AudioManager) getSystemService(Context.AUDIO_SERVICE);

        View numbers = (View) findViewById(R.id.number);  //numbers
        numbers.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent numbersIntent = new Intent(MainActivity.this, numbers.class);
                Toast.makeText(MainActivity.this,"Open the list of numbers",Toast.LENGTH_SHORT).show();
                startActivity(numbersIntent);
            }
        });
        View family = (View) findViewById(R.id.family); //family members
        family.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent familyIntent = new Intent(MainActivity.this, family.class);
                Toast.makeText(MainActivity.this,"Open the list of family members",Toast.LENGTH_SHORT).show();
                startActivity(familyIntent);
            }
        });

       View general = (View) findViewById(R.id.general); //general routine words
        general.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent generalIntent = new Intent(MainActivity.this, routine_word.class);
                Toast.makeText(MainActivity.this,"Open the list of general words",Toast.LENGTH_SHORT).show();
                startActivity(generalIntent);
            }
        });
        View colors = (View) findViewById(R.id.color);  //colors
        colors.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent colorsIntent = new Intent(MainActivity.this, colors.class);
                Toast.makeText(MainActivity.this,"Open the list of colors",Toast.LENGTH_SHORT).show();
                startActivity(colorsIntent);
            }
        });
        View tens =(View) findViewById(R.id.tens); //Tens
        tens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tensIntent =new Intent(MainActivity.this,tens.class);
                Toast.makeText(MainActivity.this,"Open the list of tens",Toast.LENGTH_SHORT).show();
                startActivity(tensIntent);
            }
        });
        View abcd =(View) findViewById(R.id.abcd); //abcd
        abcd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tensIntent =new Intent(MainActivity.this,abcd.class);
                Toast.makeText(MainActivity.this,"Open the list of ABCD",Toast.LENGTH_SHORT).show();
                startActivity(tensIntent);
            }
        });

        ImageView n=(ImageView)  findViewById(R.id.N1); //button for numbers sound
        n.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                play_sound(R.raw.numbers_en_in_1);

            }
        });
        ImageView f=(ImageView)  findViewById(R.id.F1); //button for family members sound
        f.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                play_sound(R.raw.family_main_en_in_1);

            }
        });
        ImageView c=(ImageView)  findViewById(R.id.C1);//button for color sound
        c.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                play_sound(R.raw.colors_en_in_1);

            }
        });
        ImageView r=(ImageView)  findViewById(R.id.R1); //button for routine word sound
        r.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                play_sound(R.raw.routine_main_en_in_1);

            }
        });
        ImageView t=(ImageView)  findViewById(R.id.T1); //button for tens sound
        t.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                play_sound(R.raw.tens_main_en_in_1);
            }
        });
        ImageView a=(ImageView)  findViewById(R.id.A1);//button for abcd sound
        a.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                play_sound(R.raw.abcd_en_in_1);

            }
        });
    }
    // play sound function for playing sound with passed sound id
    //Todo :play sound
    private void play_sound(int sound_id){
        releaseMediaPlayer();
        int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            mMediaPlayer = MediaPlayer.create(MainActivity.this,sound_id);
            mMediaPlayer.start();
            mMediaPlayer.setOnCompletionListener(mCompletion);
        }
    }
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();  //onstop of activity releasing player
    }

    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {

            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater i=getMenuInflater();
        i.inflate(R.menu.hom_page,menu); // set menu as R.menu.menu
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.notes) {
            Toast.makeText(MainActivity.this,"Notes open",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, Note.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
