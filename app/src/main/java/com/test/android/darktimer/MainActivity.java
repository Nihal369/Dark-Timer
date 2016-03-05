package com.test.android.darktimer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

    public class MainActivity extends AppCompatActivity {

    int time,minutes,seconds;
    SeekBar seekBar;
    String min,sec;
    MediaPlayer tick;
    public void Start(View view)
    {
        seekBar.setVisibility(View.INVISIBLE);
        final Button button=(Button)findViewById(R.id.button);
        button.setVisibility(View.INVISIBLE);
        final TextView clock=(TextView)findViewById(R.id.clock);
        final MediaPlayer m;
        tick=MediaPlayer.create(this,R.raw.tick);
        m=MediaPlayer.create(this,R.raw.airhorn);
        final ImageView glass=(ImageView)findViewById(R.id.hourglass);
        glass.animate().rotation(3600*60).setDuration(600000);
        CountDownTimer countDownTimer=new CountDownTimer(time,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tick.start();
                int tym=(int)millisUntilFinished/1000;
                minutes = (int)tym/60;
                seconds = (int) tym - (minutes * 60);
                min=String.format("%02d",minutes);
                sec=String.format("%02d",seconds);
                clock.setText("00:"+min+":"+sec);
            }

            @Override
            public void onFinish() {
                clock.setText("00:00:00");
                m.start();
                seekBar.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
                glass.animate().cancel();
            }
        };
        countDownTimer.start();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        seekBar=(SeekBar)findViewById(R.id.seekBar);
        final TextView timer=(TextView)findViewById(R.id.clock);
        seekBar.setMax(600);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, final int progress, boolean fromUser) {
                minutes = (int)progress/60;
                seconds = progress - (minutes * 60);
                min=String.format("%02d",minutes);
                sec=String.format("%02d",seconds);
                time=(progress*1000);
                timer.setText("00:"+min+":"+sec);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
