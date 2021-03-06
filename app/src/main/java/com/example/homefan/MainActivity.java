package com.example.homefan;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    ToggleButton toggeButton;
    ImageView imageView;
    ObjectAnimator rotateAnimator;
    Switch switchButton;
    SeekBar seekBar;
    int index;
    final int SPEED[] = {0, 5000, 3000, 1000};
    GradientDrawable gd = new GradientDrawable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ToggleButton toggleButton = findViewById(R.id.toggleButton);
        ImageView imageView = findViewById(R.id.imageView);
        Switch switchButton = findViewById(R.id.switch1);
        SeekBar seekBar = findViewById(R.id.seekBar);
        rotateAnimator=ObjectAnimator.ofFloat(imageView, "rotation", 0,360);
        rotateAnimator.setDuration(1000);
        rotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
        rotateAnimator.setInterpolator(new LinearInterpolator());
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true){
                    rotateAnimator.setDuration(SPEED[index]);
                    rotateAnimator.start();
                }
                else{
                    rotateAnimator.end();
                }
            }
        });
        gd.setShape(GradientDrawable.RECTANGLE);
        gd.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        gd.setGradientRadius(330);

        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true){
                    gd.setColors(new int[]{ Color.YELLOW , Color.TRANSPARENT });
                    imageView.setBackground(gd);

                }
                else{
                    imageView.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //rotate the fan based on progress parameter
                if (progress == 0){
                    index = 0;
                }
                else if (progress == 1){
                    index = 1;
                }
                else if (progress == 2){
                    index = 2;
                }
                else if (progress == 3){
                    index = 3;
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
}