package milanfin.sprintstarttimer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        int asd1 = getIntent().getIntExtra("marks", 0);
        int asd2 = getIntent().getIntExtra("set", 0);
        int asd3 = getIntent().getIntExtra("min", 0);
        int asd4 = getIntent().getIntExtra("max", 0);

        //slidereihin sopivat arvot
        final SeekBar onMarksBar = (SeekBar) findViewById(R.id.onMarksSeekBar);
        onMarksBar.setProgress(asd1-1);
        final SeekBar setBar = (SeekBar) findViewById(R.id.setSeekBar);
        setBar.setProgress(asd2-10);
        final SeekBar minBar = (SeekBar) findViewById(R.id.minSeekBar);
        minBar.setProgress(asd3-1);
        final SeekBar maxBar = (SeekBar) findViewById(R.id.maxSeekBar);
        maxBar.setProgress(asd4-1);


        //asetetaan numeroarvojen näyttöarvot alkuun
        final TextView onMarksDelayText = (TextView) findViewById(R.id.onMarksDelayText);
        onMarksDelayText.setText(String.valueOf(onMarksBar.getProgress()+1));
        final TextView onSetDelayText = (TextView) findViewById(R.id.onSetDelayText);
        onSetDelayText.setText(String.valueOf(setBar.getProgress()+10));
        final TextView onStartMinDelayText = (TextView) findViewById(R.id.onStartDelayMinText);
        onStartMinDelayText.setText(String.valueOf(minBar.getProgress()+1));
        final TextView onStartMaxDelayText = (TextView) findViewById(R.id.onStartDelayMaxText);
        onStartMaxDelayText.setText(String.valueOf(maxBar.getProgress()+1));



        //listenerit, jotka asettaa näkyviin numeroarvot, kun sliderien arvot muuttuu
        onMarksBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //handler for seekbar, that determines how long we must wait to begin after hitting
            //the start button
            public void onStopTrackingTouch(SeekBar seekBar){
                //not needed, causes error if removed
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar){
                //not needed, causes error if removed
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                //TextView onMarksText = (TextView) findViewById(R.id.onMarksText);
                //onMarksText.setText(String.valueOf(progress + 1));

                onMarksDelayText.setText(String.valueOf(progress+1));

            }
        });



        setBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //handler for seekbar, that determines how long we must wait to begin after hitting
            //the start button
            public void onStopTrackingTouch(SeekBar seekBar){
                //not needed, causes error if removed
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar){
                //not needed, causes error if removed
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                //TextView onMarksText = (TextView) findViewById(R.id.onMarksText);
                //onMarksText.setText(String.valueOf(progress + 1));

                onSetDelayText.setText(String.valueOf(progress+10));

            }
        });

        minBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //handler for seekbar, that determines how long we must wait to begin after hitting
            //the start button
            public void onStopTrackingTouch(SeekBar seekBar){
                //not needed, causes error if removed
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar){
                //not needed, causes error if removed
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                //TextView onMarksText = (TextView) findViewById(R.id.onMarksText);
                //onMarksText.setText(String.valueOf(progress + 1));

                onStartMinDelayText.setText(String.valueOf(progress+1));

                if (minBar.getProgress() > maxBar.getProgress()){
                    maxBar.setProgress(progress);
                    onStartMaxDelayText.setText(String.valueOf(maxBar.getProgress()+1));
                }

            }
        });

        maxBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //handler for seekbar, that determines how long we must wait to begin after hitting
            //the start button
            public void onStopTrackingTouch(SeekBar seekBar){
                //not needed, causes error if removed
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar){
                //not needed, causes error if removed
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                //TextView onMarksText = (TextView) findViewById(R.id.onMarksText);
                //onMarksText.setText(String.valueOf(progress + 1));

                onStartMaxDelayText.setText(String.valueOf(progress+1));

                if (minBar.getProgress() > maxBar.getProgress()){
                    minBar.setProgress(progress);
                    onStartMinDelayText.setText(String.valueOf(minBar.getProgress()+1));
                }
            }
        });



    }

    public void buttonClick(View view){

        SeekBar onMarksBar = (SeekBar) findViewById(R.id.onMarksSeekBar);

        SeekBar setBar = (SeekBar) findViewById(R.id.setSeekBar);

        SeekBar minBar = (SeekBar) findViewById(R.id.minSeekBar);

        SeekBar maxBar = (SeekBar) findViewById(R.id.maxSeekBar);


        Intent intent = new Intent();
        intent.putExtra("onMarksDelay",onMarksBar.getProgress()+1);
        intent.putExtra("setDelay",setBar.getProgress()+10);
        intent.putExtra("minStartDelay",minBar.getProgress()+1);
        intent.putExtra("maxStartDelay",maxBar.getProgress()+1);

        setResult(RESULT_OK, intent);
        finish();
        //Intent intent = new Intent(Main2Activity.this,MainActivity.class);
        //startActivity(intent);
        //setContentView(R.layout.activity_main);
    }
}
