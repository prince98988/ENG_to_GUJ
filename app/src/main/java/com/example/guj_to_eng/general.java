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

public class general extends AppCompatActivity {
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
        words.add(new word("લખો/લખવું","Write",R.drawable.general_write,R.raw.write_en_in_1));
        words.add(new word("વાંચવું","Read",R.drawable.general_read,R.raw.read_en_in_1));
        words.add(new word("ખાવું/જમવું","Eat",R.drawable.general_eat,R.raw.eat_en_in_1));
        words.add(new word("સાંભળો/સાંભળવું","Hear",R.drawable.general_hear,R.raw.hear_en_in_1));
        words.add(new word("ધ્યાનથી સાંભળવું","Listen (hear with intention)",R.drawable.general_listen,R.raw.listen_en_in_1));
        words.add(new word("આવો/આવવું","Come",R.drawable.general_come,R.raw.come_en_in_1));
        words.add(new word("ભેગા/એકઠું કરવું","Gather",R.drawable.general_gather,R.raw.gather_en_in_1));
        words.add(new word("ભીડ/ટોળું","Crowd",R.drawable.general_crowd,R.raw.crowd_en_in_1));
        words.add(new word("ઊંઘ/ઊંઘવું","Sleep",R.drawable.general_sleep,R.raw.sleep_en_in_1));



        WordAdapter adapter =new WordAdapter(this,words,R.color.green);
        ListView listview = (ListView)findViewById(R.id.general);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                word word=words.get(position);
                releaseMediaPlayer();
                Toast.makeText(general.this, "List item clicked", Toast.LENGTH_SHORT).show();
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mMediaPlayer = MediaPlayer.create(general.this, word.getmAudioResourceId());
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

