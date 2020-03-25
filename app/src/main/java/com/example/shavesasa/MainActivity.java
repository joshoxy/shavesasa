package com.example.shavesasa;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.Model.User;
import com.example.shavesasa.Common.Common;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKit;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_reg;
    EditText fname;
    EditText lname;
    EditText phone;
    EditText reg_email;
    EditText pass;
    TextView txt_sign_in;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    CollectionReference new_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth =  FirebaseAuth.getInstance();
        new_user = FirebaseFirestore.getInstance().collection("User");

        //Check if user is logged in
        if (firebaseAuth.getCurrentUser() != null){
            Toast.makeText(this, "User Already Logged In", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        }

        progressDialog = new ProgressDialog(this);

        btn_reg = (Button)findViewById(R.id.btn_reg);
        fname = (EditText)findViewById(R.id.fname);
        lname = (EditText)findViewById(R.id.lname);
        phone = (EditText)findViewById(R.id.phone);
        reg_email = (EditText)findViewById(R.id.email);
        pass = (EditText)findViewById(R.id.pass);
        txt_sign_in = (TextView)findViewById(R.id.txt_signin);

        btn_reg.setOnClickListener(this);
        txt_sign_in.setOnClickListener(this);

        if (firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            finish();
        }

    }

    @Override
    public void onClick(View v) {
        if (v == btn_reg){
            registerUser();
        }

        if (v == txt_sign_in){
            startActivity(new Intent(getApplicationContext(), sign_in.class));
            finish();
        }

    }

    private void registerUser() {
        String first = fname.getText().toString().trim();
        String last = lname.getText().toString().trim();
        String email = reg_email.getText().toString();
        String Phone = phone.getText().toString();
        String password = pass.getText().toString();

        if (TextUtils.isEmpty(first)){
            Toast.makeText(this, "Please enter first name", Toast.LENGTH_SHORT).show();
            return;

        }

        if (TextUtils.isEmpty(last)){
            Toast.makeText(this, "Please enter last name", Toast.LENGTH_SHORT).show();
            return;

        }

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;

        }

        if (TextUtils.isEmpty(Phone)){
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

        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Error ! " +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

       /* User user = new User(first,last,Email,Phone,password);
        new_user.document().set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                finish();
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });*/

    }
}
