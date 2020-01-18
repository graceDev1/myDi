package com.example.codler_fun.mycoachdbt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Login_user extends AppCompatActivity {

    private EditText phone;

    Button btn_lo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        phone = findViewById(R.id.phone_number);

        btn_lo = findViewById(R.id.btn_louser);


        btn_lo.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                String number = phone.getText().toString().trim();

                    if (number.isEmpty()){
                        phone.setError("Enter a valid phone number");
                        phone.requestFocus();

                    }
                    else {
                        Intent verifiy = new Intent(Login_user.this, Verify.class);
                        verifiy.putExtra("phonenumber", number);
                        startActivity(verifiy);
                        finish();
                    }
                }

        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null)
        {
            Intent intent = new Intent(Login_user.this,MainActivity.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }
    }
}
