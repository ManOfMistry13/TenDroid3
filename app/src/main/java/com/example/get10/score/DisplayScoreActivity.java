package com.example.get10.score;


import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.get10.DataBase;
import com.example.get10.PlayActivity;
import com.example.get10.Position;
import com.example.get10.R;
import com.example.get10.TheApplication;


public class DisplayScoreActivity extends AppCompatActivity {

    TextView textView;
    TheApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_score);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textView = (TextView) findViewById(R.id.fab);

        app = (TheApplication) (this.getApplication());

        String s = "";
        for(String n : DataBase.Score.keySet()){
            s += "\n "+n+"  :  "+Integer.toString(DataBase.Score.get(n));
        }

        textView.setText(s);  //app.getName()+" : "+Integer.toString(DataBase.Score.get(app.getName())));

    }



    //System.out.println(app.getName()+" : "+(DataBase.Score.get(app.getName())));

        /*((TextView) findViewById(R.id.name)).setText("Name :" +String.valueOf(app.getName()));

        ((TextView) findViewById(R.id.tv_score)).setText("Score : " + String.valueOf(app.getScore()));*/



}
