package com.zf.fooder;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button signUpButton, signInButton;
    TextView txtSlogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signUpButton = findViewById(R.id.signUpButton);
        signInButton = findViewById(R.id.signInButton);
        txtSlogan = findViewById(R.id.txtSlogan);

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/NABILA.TTF");
        txtSlogan.setTypeface(face);
    }

    public void clickSignIn(View view) {
        Intent signIn = new Intent(this, com.zf.fooder.signIn.class);
        startActivity(signIn);
    }

    public void clickSignUp(View view) {
        Intent signUp = new Intent(this, Sign_Up.class);
        startActivity(signUp);
    }
}
