package com.example.shavesasa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class sign_in extends AppCompatActivity implements View.OnClickListener {

    Button btn_sign_in;
    EditText email_sign;
    EditText pass_sign;
    TextView txt_signup;
    FirebaseAuth firebaseAuth;


    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        firebaseAuth = FirebaseAuth.getInstance();

        //Check if user is logged in
        if (firebaseAuth.getCurrentUser() != null){
            Toast.makeText(this, "User Already Logged In", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        }


        btn_sign_in = (Button) findViewById(R.id.btn_sign);
        email_sign = (EditText) findViewById(R.id.email_sign);
        pass_sign = (EditText) findViewById(R.id.pass_sign);
        txt_signup = (TextView) findViewById(R.id.txt_signup);

        btn_sign_in.setOnClickListener(this);
        txt_signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btn_sign_in){
            userLogin();

        }

        if (v == txt_signup){
            finish();
            startActivity(new Intent(this, MainActivity.class));

        }
    }

    private void userLogin() {
        String email = email_sign.getText().toString().trim();
        String password = pass_sign.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter phone", Toast.LENGTH_SHORT).show();
            return;

        }

        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;

        }

        if (password.length() < 6){
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }


      /*  progressDialog.setMessage("Please wait...");
        progressDialog.show();*/

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                     //progressDialog.dismiss();
                     if (task.isSuccessful()){
                         Toast.makeText(getApplicationContext(), "Successfully Logged in", Toast.LENGTH_SHORT).show();
                         startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                         finish();
                     }else{
                         Toast.makeText(getApplicationContext(), "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                     }
                    }
                });

    }
}
