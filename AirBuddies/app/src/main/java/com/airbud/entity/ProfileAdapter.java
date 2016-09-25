package com.airbud.entity;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbud.R;

import java.util.List;

/**
 * Created by Victor on 9/24/2016.
 */

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {

    public static class ProfileViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView profileName;
        TextView profileAge;
        ImageView profilePhoto;

        ProfileViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            profileName = (TextView)itemView.findViewById(R.id.profileName);
            profileAge = (TextView)itemView.findViewById(R.id.profileAge);
            profilePhoto = (ImageView)itemView.findViewById(R.id.profilePic);
        }
    }

    private List<Profile> peoples;

    public ProfileAdapter(List<Profile> peoples) {
        this.peoples = peoples;
    }

    @Override
    public int getItemCount() {
        return peoples.size();
    }

    @Override
    public ProfileViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.profile_item, viewGroup, false);
        ProfileViewHolder pvh = new ProfileViewHolder(v);
        return pvh;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(ProfileViewHolder profileViewHolder, int i) {
        profileViewHolder.profileName.setText(peoples.get(i).getName());
        profileViewHolder.profileAge.setText(peoples.get(i).getAge());
        profileViewHolder.profilePhoto.setImageBitmap(peoples.get(i).getBm());
    }
}
