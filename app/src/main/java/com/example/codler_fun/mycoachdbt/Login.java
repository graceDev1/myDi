package com.example.codler_fun.mycoachdbt;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

     EditText email,password1;
    TextView reg;
    Button btn_logn;


     Intent MainAct;
     ProgressBar progressBar;
     FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.logn_text);
        password1 = findViewById(R.id.pass_text);
        reg = findViewById(R.id.go_reg);
        btn_logn = findViewById(R.id.btn_logn);

        MainAct = new Intent(Login.this,Docter_side.class);
        progressBar = findViewById(R.id.progress_reg);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

//        progressBar.setVisibility(View.INVISIBLE);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerActivity = new Intent(Login.this,Register.class);
                startActivity(registerActivity);
                finish();
            }
        });

        btn_logn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                progressBar.setVisibility(View.VISIBLE);
//                btn_logn.setVisibility(View.INVISIBLE);

                final String mail = email.getText().toString();
                final String password = password1.getText().toString();

                if (mail.isEmpty() || password.isEmpty()) {
                    showMessage("Please Verify All Field");
//                    btn_logn.setVisibility(View.VISIBLE);
//                    progressBar.setVisibility(View.INVISIBLE);
                }
                else
                {
                    signIn(mail,password);
                }




            }
        });


    }


    private void signIn(String mail, String password) {


        mAuth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if (task.isSuccessful()) {

//                    progressBar.setVisibility(View.INVISIBLE);
//                    btn_logn.setVisibility(View.VISIBLE);
                    updateUI();

                }
                else {
                    showMessage(task.getException().getMessage());
//                    btn_logn.setVisibility(View.VISIBLE);
//                    progressBar.setVisibility(View.INVISIBLE);
                }


            }
        });



    }

    private void updateUI() {

        startActivity(MainAct);
        finish();

    }

    private void showMessage(String text) {

        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null) {
            //user is already connected  so we need to redirect him to home page
            updateUI();

        }



    }
}
