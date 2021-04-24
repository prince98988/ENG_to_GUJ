package com.example.guj_to_eng;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class routine_word extends AppCompatActivity {
    private AudioManager mAudioManager;
    private MediaPlayer mMediaPlayer;

    private MediaPlayer.OnCompletionListener mCompletion =new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mp) {
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
        setContentView(R.layout.routine_word);
        mAudioManager=(AudioManager) getSystemService(Context.AUDIO_SERVICE);

        View numbers = (View) findViewById(R.id.r11);  // Daily activity
        numbers.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent numbersIntent = new Intent(routine_word.this, general.class);
                Toast.makeText(routine_word.this,"Open the list of Daily Activity",Toast.LENGTH_SHORT).show();
                startActivity(numbersIntent);
            }
        });
        View family = (View) findViewById(R.id.r22);// Days of week
        family.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent familyIntent = new Intent(routine_word.this, day.class);
                Toast.makeText(routine_word.this,"Open the list of Days in week",Toast.LENGTH_SHORT).show();
                startActivity(familyIntent);
            }
        });

        View general = (View) findViewById(R.id.r33); // Body parts
        general.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent generalIntent = new Intent(routine_word.this, body_part.class);
                Toast.makeText(routine_word.this,"Open the list of Body Parts",Toast.LENGTH_SHORT).show();
                startActivity(generalIntent);
            }
        });
        View colors = (View) findViewById(R.id.r44); // vegetable
        colors.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent colorsIntent = new Intent(routine_word.this, vegetables.class);
                Toast.makeText(routine_word.this,"Open the list of vegetable ",Toast.LENGTH_SHORT).show();
                startActivity(colorsIntent);
            }
        });

        ImageView r1=(ImageView)  findViewById(R.id.r1); // daily activity
        r1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
              play_sound(R.raw.daily_activity_en_in_1);
            }
        });
        ImageView f=(ImageView)  findViewById(R.id.r2);   //days of week
        f.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                play_sound(R.raw.days_of_week_en_in_1);
            }
        });
        ImageView c=(ImageView)  findViewById(R.id.r3);// body parts
        c.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                play_sound(R.raw.parts_of_the_body_en_in_1);

            }
        });
        ImageView r=(ImageView)  findViewById(R.id.r4); //vegetables
        r.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                play_sound(R.raw.vegetables_en_in_1);
            }
        });


    }
    //play sound function for playing sound with passed sound id
    private void play_sound(int sound_id){
        releaseMediaPlayer();
        int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            mMediaPlayer = MediaPlayer.create(routine_word.this,sound_id);
            mMediaPlayer.start();
            mMediaPlayer.setOnCompletionListener(mCompletion);
        }
    }
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {

            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
}
