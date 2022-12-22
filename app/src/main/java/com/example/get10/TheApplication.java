/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.get10;

import android.app.Application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import tendroid.model.PositionList;
import tendroid.model.TenGame;


/**
 *
 * @author eleph
 */
public class TheApplication extends Application {


    TenGame theGame;
    int[] ns = {1, 1, 1, 1, 1,
            2, 2, 2, 2, 2,
            3, 3, 3, 3, 3,
            4, 4, 4, 4, 4,
            5, 5, 5, 5, 5 };
    int score = 0;
    //consiste a vérifier s'il
    boolean s=true;
    String name;

    @Override
    public void onCreate() {
        super.onCreate();
        theGame = new TenGame (ns);
        theGame = new TenGame(Grille());
        score=0;
    }

   public TenGame getGame() {

        return theGame;
    }

    public boolean isAchieved(){
        for(int i=0; i<theGame.nbCol();i++){
            for(int j=0; j<theGame.nbLig();j++){
                if(theGame.get(new Position(i,j))>=10){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean youLose() {
        PositionList pl = theGame.allPositions();
        for (int i = 0; i < pl.size(); i++) {
            PositionList pl2 = theGame.getGroup((tendroid.model.Position)pl.get(i));

            if (pl2.size() >= 2) {
                return false;
            }
        }
        return true;
    }

    public void reset(){
        score=0;
        s=true;
        theGame = new TenGame(Grille());

    }
    //chiffre aléa
    public int[] Grille() {
        int [] tab=new int[25];
        int cpt=0;
        for(int i=0; i<5;i++){
            for(int j=0; j<5;j++){
                double rand=Math.random();
                if(rand<0.4) tab[cpt]=1;
                else if (rand<0.7) tab[cpt]=2;
                else tab[cpt]=3;
                cpt++;
            }
        }
        return tab;
    }


    public int getScore() {

        return score;
    }

    public void setName(String s){
        name = s;
    }
    public String getName(){
        return name;
    }





}