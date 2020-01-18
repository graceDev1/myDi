package com.example.codler_fun.mycoachdbt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Medoc extends AppCompatActivity {

    private RecyclerView list_med;
    private DatabaseReference mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medoc);

        mData = FirebaseDatabase.getInstance().getReference().child("medicament");
        mData.keepSynced(true);

        list_med = findViewById(R.id.list_medoc);
        list_med.setHasFixedSize(true);
        list_med.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<clsMedoc,MedocViewHorlder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<clsMedoc, MedocViewHorlder>
                (clsMedoc.class,R.layout.medoc_row,MedocViewHorlder.class,mData) {
            @Override
            protected void populateViewHolder(MedocViewHorlder viewHolder, clsMedoc model, int position) {
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDescription(model.getDescription());

            }
        };
        list_med.setAdapter(firebaseRecyclerAdapter);

    }

    public static class MedocViewHorlder extends RecyclerView.ViewHolder
    {
        View mView;

        public MedocViewHorlder(View itemView)
        {
            super(itemView);
            mView = itemView;
        }

        public void setTitle(String title)
        {
            TextView post_title = mView.findViewById(R.id.m_title);
            post_title.setText(title);
        }

        public void setDescription(String description)
        {
            TextView post_desc = mView.findViewById(R.id.m_desc);
            post_desc.setText(description);
        }
    }
}
