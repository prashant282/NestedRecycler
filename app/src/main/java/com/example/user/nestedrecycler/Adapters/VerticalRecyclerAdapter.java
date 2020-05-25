package com.example.user.nestedrecycler.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.nestedrecycler.Models.HorizontalModel;
import com.example.user.nestedrecycler.Models.VerticalModel;
import com.example.user.nestedrecycler.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;

import java.util.ArrayList;
import java.util.zip.Inflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class VerticalRecyclerAdapter extends RecyclerView.Adapter<VerticalRecyclerAdapter.VerticalRecyclerViewHolder> {

    Context context;
    ArrayList<VerticalModel> arrayList;


    public VerticalRecyclerAdapter(Context context, ArrayList<VerticalModel> arrayList) {
        super();
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public VerticalRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vertical_layout,parent,false);
        return new VerticalRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VerticalRecyclerViewHolder holder, int position) {
        final VerticalModel verticalModel=arrayList.get(position);
        String title=verticalModel.getTitle();
        ArrayList<HorizontalModel> singleItem=verticalModel.getArrayList();

        holder.textViewTitles.setText(title);
        final HorizontalRecyclerAdapter horizontalRecyclerAdapter =new HorizontalRecyclerAdapter(context,singleItem);

        holder.recyclerView2.setHasFixedSize(true);
        holder.recyclerView2.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));

        holder.recyclerView2.setAdapter(horizontalRecyclerAdapter);
        holder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,verticalModel.getTitle(),Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return this.arrayList.size();
    }

    public class VerticalRecyclerViewHolder extends RecyclerView.ViewHolder{
        RecyclerView recyclerView2;
        TextView textViewTitles;
        Button btnMore;
        public VerticalRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView2=itemView.findViewById(R.id.recyclerView2);
            textViewTitles=itemView.findViewById(R.id.textViewTitles);
            btnMore=itemView.findViewById(R.id.btnMore);
        }
    }
}
