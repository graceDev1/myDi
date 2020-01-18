package com.example.codler_fun.mycoachdbt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.codler_fun.mycoachdbt.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Glycemie extends AppCompatActivity {

    private EditText glycemi;
    private Button btnGly;
    DatabaseReference reference;
    FirebaseUser  firebaseUser;
    Intent intent;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glycemie);
        glycemi = findViewById(R.id.edit_glycemie);
        btnGly = findViewById(R.id.btn_gly);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();



        btnGly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String gly = glycemi.getText().toString();
                calendar = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy / MM / dd ");
                String strDate = simpleDateFormat.format(calendar.getTime());
                if (!gly.equals("")){
                    addGlycemie(firebaseUser.getUid(),gly,strDate);
                }else
                {
                    Toast.makeText(Glycemie.this,"You can't send empty message"
                            ,Toast.LENGTH_LONG).show();
                }
                Toast.makeText(Glycemie.this,"Save with success"
                        ,Toast.LENGTH_LONG).show();
                glycemi.setText("");
            }


        });


    }

    private void addGlycemie(String userid,String rate,String cdate)
    {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("userid",userid);
        hashMap.put("rate",rate);
        hashMap.put("date",cdate);
        reference.child("glycemie").push().setValue(hashMap);

    }
}
