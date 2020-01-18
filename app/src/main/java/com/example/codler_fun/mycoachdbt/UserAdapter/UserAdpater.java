package com.example.codler_fun.mycoachdbt.UserAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.codler_fun.mycoachdbt.R;
import com.example.codler_fun.mycoachdbt.model.User;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdpater extends RecyclerView.Adapter<UserAdpater.ViewHolder>
{
    private Context mContext;
    private List<User> mUser;

    public UserAdpater(Context mContext, List<User> mUser) {
        this.mContext = mContext;
        this.mUser = mUser;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.user_items,parent,false);
        return new UserAdpater.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = mUser.get(position);
        holder.username.setText(user.getUsername());
        if (user.getImageUrl().equals("default"))
        {
            holder.profile_image.setImageResource(R.mipmap.ic_launcher);
        }
        else{
            Glide.with(mContext).load(user.getImageUrl()).into(holder.profile_image);
        }

    }

    @Override
    public int getItemCount() {
        return mUser.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView username;
        public CircleImageView profile_image;

        public ViewHolder(View itemView)
        {
            super(itemView);
            profile_image = itemView.findViewById(R.id.profile_image);
            username = itemView.findViewById(R.id.phone_user);
        }
    }
}
