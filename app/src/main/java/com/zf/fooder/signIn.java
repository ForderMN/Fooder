package com.zf.fooder;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.zf.fooder.Model.User;

public class signIn extends AppCompatActivity {

    EditText phoneNum, password;
    Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }

    public void clickSignIn(View view) {
        final ProgressDialog mDialog = new ProgressDialog(signIn.this);
        phoneNum = findViewById(R.id.phoneNum);
        password = findViewById(R.id.password);
        signInButton = findViewById(R.id.signInButton);
        // initialize database
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference user_table = database.getReference("User");
        mDialog.setMessage("Please wait...");
        mDialog.show();
        user_table.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {
                // check user is exist, then get user's information
                User user = dataSnapshot.child(phoneNum.getText().toString()).getValue(User.class);
                if (dataSnapshot.child(phoneNum.getText().toString()).exists()) {
                    mDialog.dismiss();
                    if (user.getPassword().equals(password.getText().toString())) {
                        Toast.makeText(signIn.this,"Sign in successfully!",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(signIn.this, "Failed to sign in!",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    mDialog.dismiss();
                    Toast.makeText(signIn.this, "User is not exist!",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
