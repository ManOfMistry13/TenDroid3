package com.example.get10;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.TextView;
import android.widget.Toast;

import android.support.v7.widget.Toolbar;


import com.example.get10.score.DisplayScoreActivity;


public class PlayActivity extends AppCompatActivity {
    TheApplication app;
    View view;
    //TextView changerScore;
    //int score = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        //changement de couleur

        view=this.getWindow().getDecorView();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        app = (TheApplication) (this.getApplication());


        //MENU toolbar
        Toolbar toolbar = findViewById(R.id.Toolbar); //get reference to toolbar
        setSupportActionBar(toolbar); // setting/replace toolbar as the ActionBar
        getActionBar();

        // nom du joueur
        TextView textView = findViewById(R.id.TEXT);
        textView.setText("Player: " + app.name);

    }

    public void onClickQuit(View v) {
        app.reset();
        finish();
    }


    public void onClickReset(View view) {
        app.reset();
        app.score = 0;
        setScore(app.score);
        ((GameView) findViewById(R.id.boardSurface)).reDraw();

    }

    public void Score() {
        app.getScore();
        ((TextView) findViewById(R.id.tv_score)).setText("Score : " + String.valueOf(app.getScore()));

        if(DataBase.Score.containsKey(app.getName()) ){
            if(DataBase.Score.get(app.getName()) < app.getScore())
                DataBase.Score.put(app.getName(), app.getScore());
        }
        else{
            DataBase.Score.put(app.getName(), app.getScore());
        }

        System.out.println(" score ; "+ app.getName()+"  "+ DataBase.Score.get(app.getName()));
    }

    public void setScore(int score) {
        ((TextView) findViewById(R.id.tv_score)).setText("Score: " + score);
    }



    //DEROULLEMENT MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.Colors:
                Toast.makeText(this, "Color selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.vert:
                view.setBackgroundResource(R.color.vert);
                Toast.makeText(this, "Green color selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.grey:
                view.setBackgroundResource(R.color.gray);
                Toast.makeText(this, "Grey color selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.orange:
                view.setBackgroundResource(R.color.orange);
                Toast.makeText(this, "Orange color selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.AfficheScore:
                this.Score();
                Intent next_page = new Intent(this, DisplayScoreActivity.class);
                startActivity(next_page);
                //return true;

            default:

        }
        return super.onOptionsItemSelected(item);

    }

            //Window alert
    public void WindowAlert() {
        String alertTitle = "";

        if (app.isAchieved()) {
        alertTitle = "You have won the GAME!!!";
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(alertTitle);
            builder.setMessage("Do you want do retry ?");
            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    onClickReset(view);
                    dialog.dismiss();
                }
            });
            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    onClickQuit(view);
                    dialog.dismiss();

                }
            });
            AlertDialog alert = builder.create();
            builder.setCancelable(true);
            builder.show();

        } else if (app.youLose()) {
            alertTitle = "You Lose the game";
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(alertTitle);
            builder.setMessage("Do you want do retry ?");
            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    onClickReset(view);
                    dialog.dismiss();

                }
            });

            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    onClickQuit(view);
                    dialog.dismiss();

                }
            });
            AlertDialog alert = builder.create();
            builder.setCancelable(true);
            builder.show();

        }

    }



}

