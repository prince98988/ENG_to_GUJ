package com.example.guj_to_eng;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
// numbers_quiz is for first quiz
public class numbers_quiz extends AppCompatActivity {
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
    private int score=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        mAudioManager=(AudioManager) getSystemService(Context.AUDIO_SERVICE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.numbers_quiz);


    }

    public void answer(View view){
        RadioButton r;
        score=0;
        r= (RadioButton) findViewById(R.id.q13);
        if (r.isChecked()){ //if 3rd option of first question is checked
            score+=1;
        }
        r= (RadioButton) findViewById(R.id.q21); //if 1rd option of second question is checked
        if (r.isChecked()){
            score+=1;
        }
        r= (RadioButton) findViewById(R.id.q32);
        if (r.isChecked()){
            score+=1;
        }
        r= (RadioButton) findViewById(R.id.q41);
        if (r.isChecked()){
            score+=1;
        }
        r= (RadioButton) findViewById(R.id.q54);
        if (r.isChecked()){
            score+=1;
        }

        TextView s=(TextView) findViewById(R.id.score);

        if (score>3){ // if score grater than 3 then play song
            int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                    AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                mMediaPlayer = MediaPlayer.create(numbers_quiz.this,R.raw.congratulations_en_in_1);
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(mCompletion);
            }
            s.setText("\"Congratulation\" \n Your Score :"+score + " | 5");
        }
        else{
            s.setText("\"Learn more and try again In quiz 2\" \n Your Score :"+score+ " | 5");
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
