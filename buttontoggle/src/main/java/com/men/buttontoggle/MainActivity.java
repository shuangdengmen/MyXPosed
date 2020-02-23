package com.men.buttontoggle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.men.buttontoggle.view.MyToggleView;

public class MainActivity extends AppCompatActivity {

    private MyToggleView myToggleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        myToggleView = findViewById(R.id.myToggle);
    }
}
