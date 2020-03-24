package com.example.shavesasa;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.Fragments.HomeFragment;
import com.Fragments.ShoppingFragment;
import com.Model.User;
import com.example.shavesasa.Common.Common;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;
    FirebaseAuth firebaseAuth;

    CollectionReference userRef;

    AlertDialog dialog;
    Button btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(HomeActivity.this);

        firebaseAuth = FirebaseAuth.getInstance();
        btn_logout = (Button) findViewById(R.id.btn_logout);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(), sign_in.class));
            }
        });

        /*if (firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, sign_in.class));
        }*/

        FirebaseUser user = firebaseAuth.getCurrentUser();

       //Initialize Firebase
        userRef = FirebaseFirestore.getInstance().collection("User");
        dialog = new  SpotsDialog.Builder().setContext(this).setCancelable(false).build();

      bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
          Fragment fragment = null;
          @Override
          public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
              if (menuItem.getItemId() == R.id.action_home)
                  fragment = new HomeFragment();
              else if (menuItem.getItemId() == R.id.action_shopping)
                  fragment = new ShoppingFragment();
              return loadFragment(fragment);
          }
      });
      bottomNavigationView.setSelectedItemId(R.id.action_home);
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
