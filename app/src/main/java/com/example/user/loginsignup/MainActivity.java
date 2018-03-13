package com.example.user.loginsignup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String username;
    TextView usernameTxt, emailTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameTxt = findViewById(R.id.usernameTxt);
        emailTxt = findViewById(R.id.emailTxt);

        User user = (User) getIntent().getSerializableExtra("USER");
        if(user != null) {
            username = user.getUsername();
            usernameTxt.setText(user.getUsername());
            emailTxt.setText(user.getEmail());
        }
    }

    protected void onStart() {
        super.onStart();
        if(username == null)
            gotoLogin();

    }

    protected void gotoLogin() {
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }
}
