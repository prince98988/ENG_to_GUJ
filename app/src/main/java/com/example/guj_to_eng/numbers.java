package com.example.guj_to_eng;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class numbers<focusChange> extends AppCompatActivity {
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
        setContentView(R.layout.numbers);
        final ArrayList<word> words=new ArrayList<word>();
        mAudioManager=(AudioManager) getSystemService(Context.AUDIO_SERVICE);


        words.add(new word("એક","One",R.drawable.number_one,R.raw.one_en_in_1));
        words.add(new word("બે","Two",R.drawable.number_two,R.raw.two_en_in_1));
        words.add(new word("ત્રણ","Three",R.drawable.number_three,R.raw.three_en_in_1));
        words.add(new word("ચાર","Four",R.drawable.number_four,R.raw.four_en_in_1));
        words.add(new word("પાંચ","Five",R.drawable.number_five,R.raw.five_en_in_1));
        words.add(new word("છ","Six",R.drawable.number_six,R.raw.six_en_in_1));
        words.add(new word("સાત","Seven",R.drawable.number_seven,R.raw.seven_en_in_1));
        words.add(new word("આઠ","Eight",R.drawable.number_eight,R.raw.eight_en_in_1));
        words.add(new word("નવ","Nine",R.drawable.number_nine,R.raw.nine_en_in_1));
        words.add(new word("દસ","Ten",R.drawable.number_ten,R.raw.ten_en_in_1));

    TextView family = (TextView) findViewById(R.id.numbers_next);
    family.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            Intent familyIntent = new Intent(numbers.this, numbers_two.class);

            startActivity(familyIntent);
        }
    });


    WordAdapter adapter =new WordAdapter(this,words,R.color.red);
        ListView listview = (ListView)findViewById(R.id.numbers1);
        listview.setAdapter(adapter);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                word word1=words.get(position);
                                                releaseMediaPlayer();

                                                Toast.makeText(numbers.this, "List item clicked", Toast.LENGTH_SHORT).show();

                                                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                                                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                                                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                                                    mMediaPlayer = MediaPlayer.create(numbers.this, word1.getmAudioResourceId());
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
