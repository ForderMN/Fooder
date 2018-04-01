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

public class Sign_Up extends AppCompatActivity {

    EditText userName, phoneNum, password;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);
    }

    public void clickSignUp(View view) {
        userName = findViewById(R.id.userName);
        phoneNum = findViewById(R.id.phoneNum);
        password = findViewById(R.id.password);
        signUp = findViewById(R.id.signUpButton);

        // initial database
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference user_table =  database.getReference("User");

        final ProgressDialog mDiaglog = new ProgressDialog(Sign_Up.this);
        mDiaglog.setMessage("Please wait...");
        mDiaglog.show();

        user_table.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // check if user is already exist
                if (!dataSnapshot.child(phoneNum.getText().toString()).exists()) {
                    mDiaglog.dismiss();
                    User user = new User(userName.getText().toString(),
                            password.getText().toString());
                    user_table.child(phoneNum.getText().toString()).setValue(user);
                    Toast.makeText(Sign_Up.this, "Register Successfully!",
                            Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    mDiaglog.dismiss();
                    Toast.makeText(Sign_Up.this, "Already exit!",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
