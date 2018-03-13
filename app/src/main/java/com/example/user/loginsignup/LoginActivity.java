package com.example.user.loginsignup;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    UserCluster userCluster;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        try {
            userCluster = UserCluster.load(this.openFileInput(getString(R.string.userCluster)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(userCluster == null) {
            userCluster = new UserCluster();
        }
        username = findViewById(R.id.usernameTxt);
        password = findViewById(R.id.passwordTxt);
    }

    public void gotoRegisterClick(View view) {
        Intent gotoRegister = new Intent(this, RegisterActivity.class);
        startActivity(gotoRegister);
        finish();
    }

    public void loginBtnClick(View view) {
        if(userCluster.exists(username.getText().toString())) {
            User user = new User(username.getText().toString(), null, password.getText().toString());
            user = userCluster.authenticate(user);
            if(user != null) {
                Intent login = new Intent(this, MainActivity.class);
                login.putExtra("USER", user);
                startActivity(login);
                finish();
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
    }
}
