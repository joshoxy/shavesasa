package com.example.shavesasa;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.shavesasa.Common.Common;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKit;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_reg;
    EditText fname;
    EditText lname;
    EditText phone;
    EditText email;
    EditText pass;
    TextView txt_sign_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_reg = (Button)findViewById(R.id.btn_reg);
        fname = (EditText)findViewById(R.id.fname);
        lname = (EditText)findViewById(R.id.lname);
        phone = (EditText)findViewById(R.id.phone);
        email = (EditText)findViewById(R.id.email);
        pass = (EditText)findViewById(R.id.pass);
        txt_sign_in = (TextView)findViewById(R.id.txt_signin);

        btn_reg.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == btn_reg){
            registerUser();
        }

        if (v == txt_sign_in){
            //Open sign in
        }

    }

    private void registerUser() {
        String first = fname.getText().toString().trim();
        String last = lname.getText().toString().trim();
        String Email = email.getText().toString();
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

        if (TextUtils.isEmpty(Email)){
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
    }
}
