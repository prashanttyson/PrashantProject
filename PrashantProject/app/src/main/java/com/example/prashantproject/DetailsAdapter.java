package com.example.prashantproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.example.prashantproject.Api.DetailsResponse;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailsAdapter extends RecyclerView.Adapter <DetailsAdapter.MyViewHolder>{

    List<DetailsResponse> personNames;
    Context context;
    public DetailsAdapter(Context context, List<DetailsResponse> personNames) {
        this.context = context;
        this.personNames = personNames;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclercardview, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
       holder.name.setText(personNames.get(position).getName());
        holder.age.setText(personNames.get(position).getAge());
        holder.location.setText(personNames.get(position).getLocation());

        Picasso.get()
                .load(personNames.get(position).getUrl())
                .placeholder(R.drawable.ic_user)
                .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                .into(holder.profile);
    }

       @Override
    public int getItemCount() {
        return personNames.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView age;
        TextView location;
        CircleImageView profile;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            age = (TextView) itemView.findViewById(R.id.age);
            location = (TextView) itemView.findViewById(R.id.location);
            profile = (CircleImageView) itemView.findViewById(R.id.profile);



        }
    }
}