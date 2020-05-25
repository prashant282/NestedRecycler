package com.example.user.nestedrecycler.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.nestedrecycler.Models.HorizontalModel;
import com.example.user.nestedrecycler.Models.VerticalModel;
import com.example.user.nestedrecycler.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class HorizontalRecyclerAdapter extends RecyclerView.Adapter<HorizontalRecyclerAdapter.HorizontalRecyclerViewHolder> {

    Context context;
    ArrayList<HorizontalModel> singleItem;
    public HorizontalRecyclerAdapter(Context context, ArrayList<HorizontalModel> singleItem) {
        this.context=context;
        this.singleItem=singleItem;
    }

    @NonNull
    @Override
    public HorizontalRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horizontal_layout,parent,false);
        return new HorizontalRecyclerAdapter.HorizontalRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalRecyclerViewHolder holder, int position) {
        final HorizontalModel horizontalModel=singleItem.get(position);
        holder.textViewTitlesHorizontal.setText(horizontalModel.getName());
        holder.ivThumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,horizontalModel.getName(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return singleItem.size();
    }

    public class HorizontalRecyclerViewHolder extends RecyclerView.ViewHolder{
        ImageView ivThumb;
        TextView textViewTitlesHorizontal;
        public HorizontalRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ivThumb=itemView.findViewById(R.id.ivThumb);
            textViewTitlesHorizontal=itemView.findViewById(R.id.textViewTitlesHorizontal);
        }
    }
}
