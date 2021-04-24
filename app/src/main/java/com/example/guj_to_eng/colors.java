package com.example.guj_to_eng;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class colors extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener mCompletion =new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };
    private AudioManager mAudioManager;
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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.colors);
        final ArrayList<word> words=new ArrayList<word>();
        mAudioManager=(AudioManager) getSystemService(Context.AUDIO_SERVICE);

        words.add(new word("સફેદ/ધોળું","White",R.drawable.color_white,R.raw.white_en_in_1));
        words.add(new word("કાળો/શ્યામ","Black",R.drawable.color_black,R.raw.black_en_in_1));
        words.add(new word("લાલ","Red",R.drawable.color_red,R.raw.red_en_in_1));
        words.add(new word("ગુલાબી","Pink",R.drawable.color_pink,R.raw.pink_en_in_1));
        words.add(new word("વાદળી/આસમાની રંગ","Blue",R.drawable.color_blue1,R.raw.blue_en_in_1));
        words.add(new word("લીલો","Green",R.drawable.color_green,R.raw.green_en_in_1));
        words.add(new word("પીળો","Yellow",R.drawable.color_mustard_yellow,R.raw.yellow_en_in_1));
        words.add(new word("નારંગી","Orange",R.drawable.color_orange,R.raw.orange_en_in_1));
        words.add(new word("રાખોડી/ભૂખરું","Gray",R.drawable.color_gray,R.raw.grey_en_in_1));
        words.add(new word("કથ્થાઈ","Brown",R.drawable.color_brown,R.raw.brown_en_in_1));
        words.add(new word("જાંબલી","Purple",R.drawable.color_blue,R.raw.purple_en_in_1));
        words.add(new word("ભૂરો જાંબુડિયો રંગ","Violet",R.drawable.color_violet,R.raw.violet_en_in_1));




        WordAdapter adapter =new WordAdapter(this,words,R.color.green1);
        ListView listview = (ListView)findViewById(R.id.colors);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                word word=words.get(position);
                releaseMediaPlayer();
                Toast.makeText(colors.this, "List item clicked", Toast.LENGTH_SHORT).show();
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mMediaPlayer = MediaPlayer.create(colors.this, word.getmAudioResourceId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletion);
                }
            }
        });
    }
    @Override
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

