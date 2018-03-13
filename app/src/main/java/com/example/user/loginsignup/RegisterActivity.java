package com.example.user.loginsignup;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;

public class RegisterActivity extends AppCompatActivity {
    EditText username, password, email;
    UserCluster userCluster;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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
        email = findViewById(R.id.emailTxt);
    }

    public void gotoLoginClick(View view) {
        Intent loginIntent = new Intent(this, LoginActivity.class);

        startActivity(loginIntent);
        finish();
    }

    public void registerBtnClick(View view) {
        if(username.getText().length() > 0 && password.getText().length() > 0 && email.getText().length() > 0) {
            if(userCluster.exists(username.getText().toString())) {
                Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show();
            } else {
                User user = new User(username.getText().toString(), email.getText().toString(), password.getText().toString());
                if(userCluster.addUser(user)) {
                    try {
                        userCluster.save(this.openFileOutput(getString(R.string.userCluster),
                                Context.MODE_PRIVATE));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    Intent loginIntent = new Intent(this, LoginActivity.class);
                    startActivity(loginIntent);
                    finish();

                } else {
                    Toast.makeText(this, "Sorry, some error occurred", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
        }
    }
}
