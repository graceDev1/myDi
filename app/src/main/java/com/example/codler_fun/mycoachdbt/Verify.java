package com.example.codler_fun.mycoachdbt;

import android.arch.core.executor.TaskExecutor;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Verify extends AppCompatActivity {

    EditText codev;
    private EditText username;
    Button btn_ver;
    private String verification;
    FirebaseAuth mAuth;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        codev = findViewById(R.id.verifier);
        username = findViewById(R.id.username);

        btn_ver = findViewById(R.id.btn_v);
        mAuth = FirebaseAuth.getInstance();

        String phonenumer = getIntent().getStringExtra("phonenumber");
        final String usern = username.getText().toString();
        System.out.print(phonenumer);
        sendvefication(phonenumer);

        btn_ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = codev.getText().toString().trim();
                if (code.isEmpty() | code.length() < 6)
                {
                    codev.setError("Enter the correct code");
                    codev.requestFocus();
                    return;
                }
                verifyCode(code,usern);

            }
        });
    }

    public void sendvefication(String number)
    {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                60,
                TimeUnit.SECONDS,
                this,
                mCallBack
        );
    }


    public void verifyCode(String code,String usr){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verification,code);
        signInWithCredential(usr,credential);
    }

    private void signInWithCredential(final String usernam,PhoneAuthCredential credential)
    {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            assert firebaseUser != null;
                            String useid = firebaseUser.getUid();
                            reference = FirebaseDatabase.getInstance().getReference("Userd").child(useid);
                            HashMap<String,String> hashMap = new HashMap<>();
                            hashMap.put("id",useid);
                            hashMap.put("username",usernam);
                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful())
                                    {
                                        Intent intent = new Intent(Verify.this,MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });


                        }
                        else{
                            Toast.makeText(Verify.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
    mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verification = s;

        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            String code = phoneAuthCredential.getSmsCode();
            String usr = username.getText().toString();
            if (code != null)
            {
                verifyCode(code,usr);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e)
        {
            Toast.makeText(Verify.this," "+e,Toast.LENGTH_LONG).show();

        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null)
        {
            Intent intent = new Intent(Verify.this,MainActivity.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }
    }


}
