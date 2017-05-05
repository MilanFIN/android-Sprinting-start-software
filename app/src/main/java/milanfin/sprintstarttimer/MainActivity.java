package milanfin.sprintstarttimer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int onMarksDelay;
    int setDelay;
    int minStartDelay;
    int maxStartDelay;
    Random r = new Random();

    Handler handler = new Handler();
    SharedPreferences sharedpreferences;
    String prefs = "savedSettings" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedpreferences = getSharedPreferences(prefs, Context.MODE_PRIVATE);

        onMarksDelay = sharedpreferences.getInt("marks", 5);
        setDelay = sharedpreferences.getInt("set", 15);
        minStartDelay = sharedpreferences.getInt("min", 3);
        maxStartDelay = sharedpreferences.getInt("max", 5);

        Button startButton = (Button) findViewById(R.id.startButton);


    }

    public void settingsClicked(View v){
        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
        intent.putExtra("marks", onMarksDelay);
        intent.putExtra("set", setDelay);
        intent.putExtra("min", minStartDelay);
        intent.putExtra("max", maxStartDelay);

        startActivityForResult(intent, 1);
    }

    public void aboutClicked(View v){
        Intent intent = new Intent(MainActivity.this, Main3Activity.class);
        startActivityForResult(intent, 1);
    }

    public void startClicked(View v) {

        final int marks = R.raw.marks;
        final int set = R.raw.set;
        final int gun = R.raw.gun_1;
        final Button startButton = (Button) findViewById(R.id.startButton);

        startButton.setEnabled(false);

        float waitTime = minStartDelay + r.nextFloat()*(maxStartDelay-minStartDelay);
        Log.d("asd", Float.toString(waitTime));
        //adding delay before playing each sound
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                playSound(marks);
            }
        }, onMarksDelay * 1000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                playSound(set);
            }
        }, setDelay * 1000 + onMarksDelay * 1000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                playSound(gun);
            }
        }, Math.round(waitTime * 1000.0 + setDelay * 1000.0 + onMarksDelay * 1000.0));


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startButton.setEnabled(true);
            }
        }, Math.round(waitTime * 1000.0 + setDelay * 1000.0 + onMarksDelay * 1000.0));

    }


    public void playSound(int asd){

        final MediaPlayer sound = MediaPlayer.create(this, asd);
        sound.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            //@Override
            public void onPrepared(MediaPlayer mediaPlayer) {

                sound.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        //mp.stop(); //not needed, as app has already stopped? anyways
                        //causes error: E/MediaPlayer: stop called in state 0
                        mp.release();
                    }
                });

            }
        });
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                onMarksDelay = data.getIntExtra("onMarksDelay",0);
                setDelay = data.getIntExtra("setDelay",0);
                minStartDelay = data.getIntExtra("minStartDelay",0);
                maxStartDelay = data.getIntExtra("maxStartDelay",0);


                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putInt("marks", onMarksDelay);
                editor.putInt("set", setDelay);
                editor.putInt("min", minStartDelay);
                editor.putInt("max", maxStartDelay);
                editor.commit();

            }
        }
    }


}
