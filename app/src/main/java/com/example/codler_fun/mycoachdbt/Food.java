package com.example.codler_fun.mycoachdbt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Food extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("aliment");
        mDatabase.keepSynced(true);
        recyclerView = findViewById(R.id.list_aliment);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }



    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<clsFood,FoodViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<clsFood, FoodViewHolder>
                (clsFood.class,R.layout.row_food,FoodViewHolder.class,mDatabase) {
            @Override
            protected void populateViewHolder(FoodViewHolder viewHolder, clsFood model, int position) {

                viewHolder.setTitle(model.getTitle());
                viewHolder.setDescription(model.getDescription());
            }

        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class FoodViewHolder extends RecyclerView.ViewHolder
    {
        View mView;
        public FoodViewHolder(View itemView)
        {
            super(itemView);
            mView = itemView;
        }
        public void setTitle(String title)
        {
            TextView post_title = (TextView) mView.findViewById(R.id.ali_title);
            post_title.setText(title);

        }
        public void setDescription(String description)
        {
            TextView desc = (TextView) mView.findViewById(R.id.ali_descr);
            desc.setText(description);
        }
    }

}
