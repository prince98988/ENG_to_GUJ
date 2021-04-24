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

public class body_part extends AppCompatActivity {
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
        words.add(new word("માથું","Head",R.drawable.body_head,R.raw.head_en_in_1));
        words.add(new word("વાળ","Hair",R.drawable.body_hair,R.raw.hair_en_in_1));
        words.add(new word("ગરદન","Neck",R.drawable.body_neck,R.raw.neck_en_in_1));
        words.add(new word("આંખ","eye",R.drawable.body_eye,R.raw.eye_en_in_1));
        words.add(new word("નાક","Nose",R.drawable.body_nose,R.raw.nose_en_in_1));
        words.add(new word("મોં/મોઢું","Mouth",R.drawable.body_mouth,R.raw.mouth_en_in_1));
        words.add(new word("ચહેરો","Face",R.drawable.body_face,R.raw.face_en_in_1));
        words.add(new word("દાંત","Teeth",R.drawable.body_teeth,R.raw.teeth_en_in_1));
        words.add(new word("જીભ","Tongue",R.drawable.body_tounge,R.raw.tong_en_in_1));
        words.add(new word("ખભો","Shoulder",R.drawable.body_shoulder,R.raw.shoulder_en_in_1));
        words.add(new word("આંગળી","Finger",R.drawable.body_fingure,R.raw.finger_en_in_1));
        words.add(new word("અંગુઠો","Thumb",R.drawable.body_thumb,R.raw.thumb_en_in_1));
        words.add(new word("પગ","Leg",R.drawable.body_leg,R.raw.legs_en_in_1));
        words.add(new word("પેટ","Stomach",R.drawable.body_stomach,R.raw.stomach_en_in_1));
        words.add(new word("હાથ","hand",R.drawable.body_hand,R.raw.hands_en_in_1));



        WordAdapter adapter =new WordAdapter(this,words,R.color.blue);
        ListView listview = (ListView)findViewById(R.id.general);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                word word=words.get(position);
                releaseMediaPlayer();
                Toast.makeText(body_part.this, "List item clicked", Toast.LENGTH_SHORT).show();
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mMediaPlayer = MediaPlayer.create(body_part.this, word.getmAudioResourceId());
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

