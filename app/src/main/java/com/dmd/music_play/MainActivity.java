package com.dmd.music_play;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;
import  java.lang.Object;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer yeulacuoi ;
    private SeekBar seekBar;
    private AudioManager audioManager ;
    private  boolean flag = false ;
    private ImageButton next ;
    private  ImageButton back ;
    private  int couAnInt;
    private int max_Duration_Musix = 0;
    private ImageView img_song ;
    private TextView tvStart;
    private TextView tvEnd;
    private long backpressTime;
    private  boolean flag_auto = true;
    private int swith = 0;
    private  int random = 0 ;
    private  boolean flag_random = true ;
    private int swith_random = 0;
    private static int QUANTITY ;
    private MainData data = new MainData();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data.readData();
        seekBar = findViewById(R.id.seekBar);
        yeulacuoi = MediaPlayer.create(this,data.getSoundElement(0));
        yeulacuoi.start();
        max_Duration_Musix = yeulacuoi.getDuration();
        seekBar.setMax(max_Duration_Musix);
        next = findViewById(R.id.btn_next_5s);
        back = findViewById(R.id.btn_back_5s);
        img_song = findViewById(R.id.image_song);
        img_song.setImageResource(data.getImgElement(0));
        tvStart = findViewById(R.id.run_time_tv);
        tvEnd = findViewById(R.id.getDuration_tv);
        tvEnd.setText(ConvertMinute(yeulacuoi.getDuration()));
        QUANTITY = data.size();
        LongClick();
        RunTimer();
        setCurrentMusic();

    }

    private void RunTimer(){
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seekBar.setProgress(yeulacuoi.getCurrentPosition());
            }
        },0,1000);

    }

    public void Play_Music(View view) {
        yeulacuoi.start();
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.zoomin);
        findViewById(R.id.btn_play).startAnimation(animation);
    }

    public void Pause_Music(View view) {
        yeulacuoi.pause();
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.bounce);
        findViewById(R.id.btn_pause).startAnimation(animation);
    }

    private void setCurrentMusic(){
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (flag){
                    yeulacuoi.seekTo(progress);
                }
                tvStart.setText(ConvertMinute(progress));
                Log.d("dmd123","count = "+String.valueOf(fromUser));
                if(progress >= max_Duration_Musix && !flag) {
                    if(flag_auto && flag_random) {
                        yeulacuoi.start();
                        seekBar.setProgress(0);
                    }else if( !flag_auto && flag_random) {
                        flag =true;
                        couAnInt++;
                        ReadData(couAnInt%QUANTITY);
                        flag = false;
                    }else if(flag_auto && !flag_random) {
                        flag =true;
                        int min  = 0;
                        int max = QUANTITY;
                        int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
                        ReadData(random_int%QUANTITY);
                        flag = false;
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                flag = true;
                if (seekBar.getProgress() == seekBar.getMax()){
                    if( !flag_auto) {
                        couAnInt++;
                        ReadData(couAnInt % QUANTITY);
                    }else {
                        yeulacuoi.start();
                        seekBar.setProgress(0);
                    }
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                flag = false ;
            }
        });
     }

    public void Next(View view) {
        int plus5s =  yeulacuoi.getCurrentPosition()+ 5000;
        if(plus5s < yeulacuoi.getDuration()) {
            seekBar.setProgress(plus5s);
            yeulacuoi.seekTo(plus5s);
        }
    }

    public void Back(View view) {
        int sub5s = yeulacuoi.getCurrentPosition() - 5000;
        seekBar.setProgress(sub5s);
        yeulacuoi.seekTo(sub5s);
    }

    public void Next_Music(View view) {
        findViewById(R.id.next_song).setAlpha(0f);
        findViewById(R.id.next_song).animate().alpha(1f).setDuration(1000);
        backpressTime = System.currentTimeMillis();
        couAnInt++;
        int a = couAnInt%QUANTITY;
        findViewById(R.id.next_song).setEnabled(false);
        ReadData(a);
        findViewById(R.id.next_song).setEnabled(true);
    }

    private  void LongClick(){
        findViewById(R.id.next_song).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                findViewById(R.id.next_song).setAlpha(0f);
                findViewById(R.id.next_song).animate().alpha(1f).setDuration(1000);
                backpressTime = System.currentTimeMillis();
                if(couAnInt > 0){
                    couAnInt--;
                }else {
                    couAnInt = QUANTITY-1;
                }
                int a = couAnInt%QUANTITY;
                findViewById(R.id.next_song).setEnabled(false);
                ReadData(a);
                findViewById(R.id.next_song).setEnabled(true);
                return true;
            }
        });
    }

    private String ConvertMinute(int totalSecs){
        totalSecs /= 1000;
        int minutes = totalSecs / 60;
        int seconds = totalSecs % 60;
        return String.format("%02d:%02d", minutes, seconds);

    }

    private void ReadData(int i){
        img_song.setImageResource(data.getImgElement(i));
        yeulacuoi.stop(); // media nhac
        yeulacuoi = MediaPlayer.create(this,data.getSoundElement(i));
        max_Duration_Musix = yeulacuoi.getDuration();
        seekBar.setMax(max_Duration_Musix);
        yeulacuoi.start();
        seekBar.setProgress(0);
        tvEnd.setText(ConvertMinute(yeulacuoi.getDuration()));
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.blink_anim);
        //animation.setDuration(1000);
        img_song.startAnimation(animation);
    }

    @Override
    public void onBackPressed() {


        if(backpressTime + 2000 > System.currentTimeMillis()){
            super.onBackPressed();
            yeulacuoi.stop();
            this.finish();
            return;
        } else {
            Toast.makeText(getBaseContext(),"Press back again to exit",Toast.LENGTH_SHORT).show();
        }
        backpressTime = System.currentTimeMillis();
    }

    public void Reset(View view) {
        couAnInt = 0;
        ReadData(0);
    }

    public void Auto_Play(View view) {
        swith++;
        if(swith%2 == 1) {
            findViewById(R.id.btn_auto).setBackgroundColor(getResources().getColor(R.color.teal_200));
            if(!flag_random){
                findViewById(R.id.btn_random).setBackgroundColor(getResources().getColor(R.color.den));
                flag_random = true;
            }
            flag_auto = false;
        }else {
            flag_auto = true;
            findViewById(R.id.btn_auto).setBackgroundColor(getResources().getColor(R.color.den));

        }
    }

    public void Random(View view) {
        swith_random++;
        if(swith_random%2 == 1) {
            findViewById(R.id.btn_random).setBackgroundColor(getResources().getColor(R.color.teal_200));
            if (!flag_auto) {
                findViewById(R.id.btn_auto).setBackgroundColor(getResources().getColor(R.color.den));
                flag_auto = true;
            }
            flag_random = false;
        }else {
            flag_random = true;
           findViewById(R.id.btn_random).setBackgroundColor(getResources().getColor(R.color.den));
       }
    }
}