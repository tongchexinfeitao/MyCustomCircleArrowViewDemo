package com.example.mycustomcirclearrowviewdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.mycustomcirclearrowviewdemo.view.MyCustomCircleArrowView;

public class MainActivity extends AppCompatActivity {

    private MyCustomCircleArrowView myCustomCircleArrowView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myCustomCircleArrowView = (MyCustomCircleArrowView) findViewById(R.id.my_view);
    }


    public void onClick(View view) {
        myCustomCircleArrowView.setColor(Color.BLUE);
    }

    public void add(View view) {
        myCustomCircleArrowView.speed();
    }

    public void slow(View view) {
        myCustomCircleArrowView.slowDown();
    }


    public void pauseOrStart(View view) {
        myCustomCircleArrowView.pauseOrStart();
    }
}
