package com.example.scorekeepingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LoserTableAdapter extends RecyclerView.Adapter<LoserTableAdapter.ViewHolder> {

    private List<LoserTableRow> mData;
    private LayoutInflater mInflater;
    private LoserTableAdapter.ItemClickListener mClickListener;

    // data is passed into the constructor
    LoserTableAdapter(Context context, List<LoserTableRow> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public LoserTableAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.losertable_row_layout, parent, false);
        return new LoserTableAdapter.ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(LoserTableAdapter.ViewHolder holder, int position) {
        LoserTableRow loserTableRow = mData.get(position);
        holder.loserscore.setText(loserTableRow.loserscore);
        holder.loserteamname.setText(loserTableRow.loser);

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView loserscore;
        TextView loserteamname;

        ViewHolder(View itemView) {
            super(itemView);
            loserscore = itemView.findViewById(R.id.loserteamscore);
            loserteamname = itemView.findViewById(R.id.loserteamname);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    LoserTableRow getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(LoserTableAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }


}
