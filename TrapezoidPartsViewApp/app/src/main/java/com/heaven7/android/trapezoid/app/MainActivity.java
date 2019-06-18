package com.heaven7.android.trapezoid.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick1(View view){
        startActivity(new Intent(this, TestTrapezoidPartsView.class));
    }

    public void onClick2(View view){
        startActivity(new Intent(this, TestTrapezoidPartsView2.class));
    }
}
