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

public class family<focusChange> extends AppCompatActivity {
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
        setContentView(R.layout.family);
        final ArrayList<word> words=new ArrayList<word>();  //arraylist is for adding list of values
        mAudioManager=(AudioManager) getSystemService(Context.AUDIO_SERVICE);


        words.add(new word("બાપ / પિતા / બાપુ","Father",R.drawable.family_father,R.raw.father_en_in_1));
        words.add(new word("મા / બા","Mother",R.drawable.family_mother,R.raw.mother_en_in_1));
        words.add(new word("દીકરો","Son",R.drawable.family_son,R.raw.son_en_in_1));
        words.add(new word("દીકરી","Daughter",R.drawable.family_daughter,R.raw.daughter_en_in_1));
        words.add(new word("ભાઈ ","Brother",R.drawable.family_younger_brother,R.raw.brother_en_in_1));
        words.add(new word("બહેન","Sister",R.drawable.family_younger_sister,R.raw.sister_en_in_1));
        words.add(new word("દાદી","Grandmother",R.drawable.family_grandmother,R.raw.grandmother_en_in_1));
        words.add(new word("દાદા","Grandfather",R.drawable.family_grandfather,R.raw.grandfather_en_in_1));



        WordAdapter adapter =new WordAdapter(this,words,R.color.yellow1); //add adapter
        ListView listview = (ListView)findViewById(R.id.family);
        listview.setAdapter(adapter);
        //on list item clicked play song
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                word word1=words.get(position);
                releaseMediaPlayer();

                Toast.makeText(family.this, "List item clicked", Toast.LENGTH_SHORT).show();

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT); // request for music streaming

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {  //if request grapted
                    mMediaPlayer = MediaPlayer.create(family.this, word1.getmAudioResourceId());
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
