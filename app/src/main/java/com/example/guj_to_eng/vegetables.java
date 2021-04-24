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

public class vegetables extends AppCompatActivity {
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
        setContentView(R.layout.general);
        final ArrayList<word> words=new ArrayList<word>();
        mAudioManager=(AudioManager) getSystemService(Context.AUDIO_SERVICE);
        words.add(new word("બટાટા","Potatoes",R.drawable.veg_potato,R.raw.potatoes_en_in_1));
        words.add(new word("ડુંગળી","Onion",R.drawable.veg_onion,R.raw.onion_en_in_1));
        words.add(new word("ટામેટું","Tomato",R.drawable.veg_tomato,R.raw.tomato_en_in_1));
        words.add(new word("કોબીજ","Cauliflower",R.drawable.veg_cabage,R.raw.cauliflower_en_in_1));
        words.add(new word("ભીંડો","Lady's Finger",R.drawable.veg_lady,R.raw.lady_en_in_1));
        words.add(new word("ગાજર","Carrot",R.drawable.veg_carrot,R.raw.carrot_en_in_1));
        words.add(new word("રીંગણ","Brinjal/Eggplant",R.drawable.veg_eggplant,R.raw.eggplant_en_in_1));
        words.add(new word("મરચું","Chilli",R.drawable.veg_chilli,R.raw.chilli_en_in_1));
        words.add(new word("ધાણા","Coriander",R.drawable.veg_cori,R.raw.coriander_en_in_1));
        words.add(new word("આદુ","Ginger",R.drawable.veg_ginger,R.raw.ginger_en_in_1));
        words.add(new word("લસણ","Garlic",R.drawable.veg_garlic,R.raw.garlic_en_in_1));
        words.add(new word("ફુલાવર","Cauliflower",R.drawable.veg_cauli,R.raw.cauliflower_en_in_1));
        words.add(new word("મૂળો","Radish",R.drawable.veg_radish,R.raw.radish_en_in_1));
        words.add(new word("કાકડી","Cucumber",R.drawable.veg_cucu,R.raw.cucumber_en_in_1));
        words.add(new word("પાલક","Spinach",R.drawable.veg_spinech,R.raw.spinach_en_in_1));



        WordAdapter adapter =new WordAdapter(this,words,R.color.yellow1);
        ListView listview = (ListView)findViewById(R.id.general);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                word word=words.get(position);
                releaseMediaPlayer();
                Toast.makeText(vegetables.this, "List item clicked", Toast.LENGTH_SHORT).show();
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mMediaPlayer = MediaPlayer.create(vegetables.this, word.getmAudioResourceId());
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

