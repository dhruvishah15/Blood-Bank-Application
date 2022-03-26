package com.example.bloodbank;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    ArrayList<Model> itemList;
    public ItemAdapter(ArrayList<Model> itemList) {

        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowitem,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {

        holder.itemText.setText(itemList.get(position).getContent());

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

        TextView itemText;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            view = itemView.findViewById(R.id.rowitem);
//            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
            itemText = itemView.findViewById(R.id.itemname);

        }

//        @Override
//        public void onClick(View v) {
//            int position = getAdapterPosition();
//            Toast.makeText(v.getContext(),itemList.get(position).getContent(),Toast.LENGTH_SHORT).show();
//            itemList.add(new Model("Added data"));
//            notifyItemInserted(0);
//
//        }

        @Override
        public boolean onLongClick(View v) {
            int position=getAdapterPosition();
            Toast.makeText(view.getContext(),"Deleted : "+itemList.get(position).getContent(),Toast.LENGTH_SHORT).show();
            itemList.remove(position);
            notifyItemRemoved(position);
            Preconfig.writeListInPref(v.getContext(),itemList );
            return true;
        }
    }
}
