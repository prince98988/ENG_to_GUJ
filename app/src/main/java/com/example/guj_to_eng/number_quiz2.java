package com.example.guj_to_eng;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class number_quiz2 extends AppCompatActivity {
    private int score;
    private MediaPlayer mMediaPlayer;

    private MediaPlayer.OnCompletionListener mCompletion = new MediaPlayer.OnCompletionListener() {
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
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };

    @Override
    protected void onCreate( Bundle savedInstanceState) {

        mAudioManager=(AudioManager) getSystemService(Context.AUDIO_SERVICE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.numbers_quiz2);

    }
   //answer1 is call when user submit the quiz
    public void answer1(View view) {
        RadioButton r;
        score = 0;
        r = (RadioButton) findViewById(R.id.q14); //que1
        if (r.isChecked()) {
            score += 1;
        }
        EditText t;
        t = (EditText) findViewById(R.id.q2);//que2
        String s;
        if(!t.getText().toString().matches("")) {
            s = t.getText().toString().replaceAll(" ", "");
            s=s.toLowerCase();
            if (s.matches("onehundredtwentythousandninehundredeightyseven") || s.matches("onelakhtwentythousandninehundredeightyseven")) {
                score += 1;
            }
        }

        t = (EditText) findViewById(R.id.q3);//que3

        if(!t.getText().toString().matches("")) {
            s = t.getText().toString().replaceAll(" ", "");

            s=s.toLowerCase();
            if (s.matches("fivemillionninehundredninety")) {
                score += 1;
            }
        }

        t = (EditText) findViewById(R.id.q4);//que4
        if(!t.getText().toString().matches("")) {
            if (t.getText().toString().matches("10012009022"))
                score += 1;
        }


        t = (EditText) findViewById(R.id.q5);//que5
        if(!t.getText().toString().matches("")) {
            if (Integer.parseInt(t.getText().toString()) == 123645)
                score += 1;
        }



        
        TextView s1 = (TextView) findViewById(R.id.score);

        if (score > 3) {
            releaseMediaPlayer();

            int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                    AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                mMediaPlayer = MediaPlayer.create(number_quiz2.this, R.raw.congratulations_en_in_1);
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(mCompletion);
            }
        s1.setText("\"Congratulation\" \n Your Score :" + score + " | 5" + "\n now you eligible for convert any number to word");
    }
        else{
            s1.setText("\"Learn more \" \n Your Score :"+score+ " | 5");
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
