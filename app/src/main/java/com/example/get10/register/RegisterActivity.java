package com.example.get10.register;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.get10.DataBase;
import com.example.get10.R;
import com.example.get10.TheApplication;
import com.example.get10.home.MainActivity;

public class RegisterActivity extends Activity {

    private EditText mName,mPasswd;
    private Button mRegisterBtn;
    private String Name,Password;

    public static final String PREFERENCE= "preference";
    public static final String PREF_NAME = "name";
    public static final String PREF_PASSWD = "passwd";
    public TheApplication app;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mName = findViewById(R.id.name);
        mPasswd = findViewById(R.id.passwd);


        mRegisterBtn = findViewById(R.id.registerBtn);
        app=(TheApplication)(this.getApplicationContext());
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validUserData()){
                    SharedPreferences mSharedPreference = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
                    SharedPreferences.Editor mEditor = mSharedPreference.edit();
                    mEditor.putString(PREF_NAME,Name);
                    mEditor.putString(PREF_PASSWD,Password);
                    mEditor.apply();
                    Name = mName.getText().toString().trim();
                    app.setName(Name);
                    finish();

                }
            }
        });

    }

    private boolean validUserData() {
        Name = mName.getText().toString().trim();
        Password = mPasswd.getText().toString().trim();

        String current_name = mName.getText().toString();
        String mdp = mPasswd.getText().toString();
        if(DataBase.Password.containsKey(current_name)){
            String current_mdp = DataBase.Password.get(current_name);
            if(current_mdp.compareTo(mdp) != 0 ){
                Toast.makeText(this, "Wrong Password", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        else{
            DataBase.Password.put(current_name,mdp);
        }

        return !(Name.isEmpty() || Password.isEmpty());
    }
    //Window alert
    //Le Popup c'était pour réinitialiser le mot de passe mais  il n'est pas terminé
    public void WindowAlert() {
        String alertTitle = "";
        alertTitle = "Mot de passe incorrect";
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                validUserData();
                dialog.dismiss();
            }
        });
    }
}
