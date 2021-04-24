package com.example.guj_to_eng;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class abcd extends AppCompatActivity {
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
    private int abcd=1;
    private int song=R.raw.a_en_in_1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.abcd);
        mAudioManager=(AudioManager) getSystemService(Context.AUDIO_SERVICE);
        View play=(View) findViewById(R.id.play);

        play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                releaseMediaPlayer();
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(abcd.this,song);
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletion);
                }

            }
        });

    }

    private void ABCD(){

        TextView text = (TextView) findViewById(R.id.a);
        TextView text1 = (TextView) findViewById(R.id.a1);
        ImageView image = (ImageView) findViewById(R.id.a2);
        if (abcd==1){
            text.setText("A");
            text1.setText("A FOR APPLE");
            image.setImageResource(R.drawable.abcd_apple);
            song=R.raw.a_en_in_1;



        }
        else if (abcd==2){
            text.setText("B");
            text1.setText("B FOR BANANA");
            image.setImageResource(R.drawable.ancd_banana);
            song=R.raw.b_en_in_1;

        }
        else if (abcd==3){
            text.setText("C");
            text1.setText("C FOR COLORS");
            image.setImageResource(R.drawable.color_main);
            song=R.raw.c_en_in_1;

        }
        else if (abcd==4){
            text.setText("D");
            text1.setText("D FOR DOG");
            image.setImageResource(R.drawable.ancd_dog);
            song=R.raw.d_en_in_1;

        }
        else if (abcd==5){
            text.setText("E");
            text1.setText("E FOR EYE");
            image.setImageResource(R.drawable.body_eye);
            song=R.raw.e_en_in_1;
        }
        else if (abcd==6){
            text.setText("F");
            text1.setText("F FOR FARM");
            image.setImageResource(R.drawable.abcd_farm);
            song=R.raw.f_en_in_1;
        }
        else if (abcd==7){
            text.setText("G");
            text1.setText("G FOR GREEN");
            image.setImageResource(R.drawable.color_green);
            song=R.raw.g_en_in_1;

        }
        else if (abcd==8){
            text.setText("H");
            text1.setText("H FOR HOUSE");
            image.setImageResource(R.drawable.abcd_house);
            song=R.raw.h_en_in_1;
        }
        else if (abcd==9){
            text.setText("I");
            text1.setText("I FOR ICE");
            image.setImageResource(R.drawable.abcd_ice);
            song=R.raw.i_en_in_1;
        }
        else if (abcd==10){
            text.setText("J");
            text1.setText("J FOR JACKET");
            image.setImageResource(R.drawable.abcd_jacket);
            song=R.raw.j_en_in_1;

        }
        else if (abcd==11){
            text.setText("K");
            text1.setText("K FOR KITE");
            image.setImageResource(R.drawable.abcd_kite);
            song=R.raw.k_en_in_1;

        }
        else if (abcd==12){
            text.setText("L");
            text1.setText("L FOR LEG");
            image.setImageResource(R.drawable.body_leg);
            song=R.raw.l_en_in_1;

        }
        else if (abcd==13){
            text.setText("M");
            text1.setText("M FOR MOTHER");
            image.setImageResource(R.drawable.family_mother);
            song=R.raw.m_en_in_1;
        }
        else if (abcd==14){
            text.setText("N");
            text1.setText("N FOR NOSE");
            image.setImageResource(R.drawable.body_nose);
            song=R.raw.n_en_in_1;

        }
        else if (abcd==15){
            text.setText("O");
            text1.setText("O FOR ORANGE");
            image.setImageResource(R.drawable.color_orange);
            song=R.raw.o_en_in_1;

        }
        else if (abcd==16){
            text.setText("P");
            text1.setText("P FOR POTATO");
            image.setImageResource(R.drawable.veg_potato);
            song=R.raw.p_en_in_1;

        }
        else if (abcd==17){
            text.setText("Q");
            text1.setText("Q FOR QUEEN");
            image.setImageResource(R.drawable.abcd_queen);
            song=R.raw.q_en_in_1;

        }
        else if (abcd==18){
            text.setText("R");
            text1.setText("R FOR ROSE");
            image.setImageResource(R.drawable.abcd_rose);
            song=R.raw.r_en_in_1;

        }
        else if (abcd==19){
            text.setText("S");
            text1.setText("S FOR SLEEP");
            image.setImageResource(R.drawable.general_sleep);
            song=R.raw.s_en_in_1;
        }
        else if (abcd==20){
            text.setText("T");

            text1.setText("T FOR TEETH");
            image.setImageResource(R.drawable.body_teeth);
            song=R.raw.t_en_in_1;
        }
        else if (abcd==21){
            text.setText("U");
            text1.setText("U FOR UMBRELLA");
            image.setImageResource(R.drawable.abcd_umbrella);
            song=R.raw.u_en_in_1;

        }
        else if (abcd==22){
            text.setText("V");
            text1.setText("V FOR VAN");
            image.setImageResource(R.drawable.abcd_van);
            song=R.raw.v_en_in_1;

        }
        else if (abcd==23){
            text.setText("W");
            text1.setText("W FOR WHALE");
            image.setImageResource(R.drawable.abcd_whale);
            song=R.raw.w_en_in_1;

        }
        else if (abcd==24){
            text.setText("X");
            text1.setText("X FOR X-RAY");
            image.setImageResource(R.drawable.abcd_xray);
            song=R.raw.x_en_in_1;


        }else if (abcd==25){
            text.setText("Y");
            text1.setText("Y FOR YELLOW");
            image.setImageResource(R.drawable.color_dusty_yellow);
            song=R.raw.y_en_in_1;

        }
        else if (abcd==26){
            text.setText("Z");
            text1.setText("Z FOR ZEBRA");
            image.setImageResource(R.drawable.abcd_zebra);
            song=R.raw.z_en_in_1;


        }


    }


    public void change(View view){
        if (abcd!=26) {
            abcd += 1;
            ABCD();
        }

    }

    public void change1(View view){
        if (abcd!=1) {
            abcd -= 1;
            ABCD();
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
