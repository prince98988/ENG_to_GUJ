package com.example.guj_to_eng;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

//number_two class is for  number grater than 10
public class numbers_two<focusChange> extends AppCompatActivity {
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
        setContentView(R.layout.numbers_two);
        final ArrayList<word> words=new ArrayList<word>();
        mAudioManager=(AudioManager) getSystemService(Context.AUDIO_SERVICE);

        TextView family = (TextView) findViewById(R.id.numbers_next1);
        family.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent familyIntent = new Intent(numbers_two.this, number_three.class);

                startActivity(familyIntent);
            }
        });


        words.add(new word("અગિયાર","Eleven",R.drawable.number_eleven,R.raw.eleven_en_in_1));
        words.add(new word("બાર","Twelve",R.drawable.number_twelve,R.raw.twelve_en_in_1));
        words.add(new word("તેર","Thirteen",R.drawable.number_thirteen,R.raw.thirteen_en_in_1));
        words.add(new word("ચૌદ","Fourteen",R.drawable.number_forteen,R.raw.fourteen_en_in_1));
        words.add(new word("પંદર","Fifteen",R.drawable.number_fifteen,R.raw.fifteen_en_in_1));
        words.add(new word("સોળ","Sixteen",R.drawable.number_sixteen,R.raw.sixteen_en_in_1));
        words.add(new word("સત્તર","Seventeen",R.drawable.number_seventeen,R.raw.seventeen_en_in_1));
        words.add(new word("અઢાર","Eighteen",R.drawable.number_eighteen,R.raw.eighteen_en_in_1));
        words.add(new word("ઓગણીસ","Nineteen",R.drawable.number_nineteen,R.raw.nineteen_en_in_1));
        words.add(new word("વીસ","Twenty",R.drawable.number_twenty,R.raw.twenty_en_in_1));
        words.add(new word("ત્રીસ","Thirty",R.drawable.number_thirty,R.raw.thirty_en_in_1));
        words.add(new word("ચાળીસ","Forty",R.drawable.number_forty,R.raw.forty_en_in_1));
        words.add(new word("પચાસ","Fifty",R.drawable.number_fifty,R.raw.fifty_en_in_1));
        words.add(new word("સાઠ","Sixty",R.drawable.number_sixty,R.raw.sixty_en_in_1));
        words.add(new word("સિત્તેર","Seventy",R.drawable.number_seventy,R.raw.seventy_en_in_1));
        words.add(new word("એંસી","Eighty",R.drawable.number_eighty,R.raw.eighty_en_in_1));
        words.add(new word("નેવું","Ninety",R.drawable.number_ninety,R.raw.ninety_en_in_1));
        words.add(new word("સો","Hundred",R.drawable.number_hundred,R.raw.hundred_en_in_1));
        words.add(new word("હજાર","Thousand",R.drawable.number_thousand,R.raw.thousand_en_in_1));
        words.add(new word("એક લાખ","One Lakhs",R.drawable.number_lakh,R.raw.lakhs_en_in_1));
        words.add(new word("દસ લાખ","Million",R.drawable.number_million,R.raw.million_en_in_1));
        words.add(new word("એક અબજ","Billion",R.drawable.number_billion,R.raw.billion_en_in_1));


        WordAdapter adapter =new WordAdapter(this,words,R.color.red);
        ListView listview = (ListView)findViewById(R.id.numbers_two);
        listview.setAdapter(adapter);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                word word1=words.get(position);
                releaseMediaPlayer();

                Toast.makeText(numbers_two.this, "List item clicked", Toast.LENGTH_SHORT).show();

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(numbers_two.this, word1.getmAudioResourceId());
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
