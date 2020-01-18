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

public class Sport extends AppCompatActivity {

    private RecyclerView list_sport;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("sport");
        mDatabase.keepSynced(true);

        list_sport = findViewById(R.id.list_sport);
        list_sport.setHasFixedSize(true);
        list_sport.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<clsSport,sportViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<clsSport, sportViewHolder>
                (clsSport.class,R.layout.sport_row,sportViewHolder.class,mDatabase) {
            @Override
            protected void populateViewHolder(sportViewHolder viewHolder, clsSport model, int position) {
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDescription(model.getDescription());

            }
        };
        list_sport.setAdapter(firebaseRecyclerAdapter);
    }

    public static class sportViewHolder extends RecyclerView.ViewHolder
    {
        View mView;
        public sportViewHolder(View itemView)
        {
            super(itemView);
            mView = itemView;
        }


        public void setTitle(String title)
        {
            TextView post_title = mView.findViewById(R.id.sp_title);
            post_title.setText(title);
        }

        public void  setDescription(String description)
        {
            TextView post_desc = mView.findViewById(R.id.sp_desc);
            post_desc.setText(description);
        }
    }
}
