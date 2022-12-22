package com.example.get10.home;

import android.app.Activity;

import android.content.Intent;

import android.os.Bundle;

import android.view.Menu;
import android.view.View;


import com.example.get10.PlayActivity;
import com.example.get10.R;
import com.example.get10.TheApplication;

import com.example.get10.register.RegisterActivity;


public class MainActivity extends Activity  {

    TheApplication app;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        app = (TheApplication)(this.getApplication());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onClickExit(View v) {

        finish();
    }
    public void onClickPlay(View v) {
                //Play Activity
        Intent playIntent = new Intent(this, PlayActivity.class);
        startActivity(playIntent);

                //Register Activity
        Intent registerActiv = new Intent(this,RegisterActivity.class);
        startActivity(registerActiv);

    }



}
