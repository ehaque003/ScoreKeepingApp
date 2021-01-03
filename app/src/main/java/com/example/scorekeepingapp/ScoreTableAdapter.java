package com.example.scorekeepingapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ScoreTableAdapter extends RecyclerView.Adapter<ScoreTableAdapter.ViewHolder> {

    private List<ScoreTableRow> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    ScoreTableAdapter(Context context, List<ScoreTableRow> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.scoretable_row_layout, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ScoreTableRow scoreTableRow = mData.get(position);
        holder.name1.setText(scoreTableRow.name1);
        holder.score1.setText(scoreTableRow.score1 + "");
        holder.name2.setText(scoreTableRow.name2);
        holder.score2.setText(scoreTableRow.score2 + "");
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name1;
        TextView score1;
        TextView name2;
        TextView score2;

        ViewHolder(View itemView) {
            super(itemView);
            name1 = itemView.findViewById(R.id.name1);
            score1 = itemView.findViewById(R.id.score1);
            name2 = itemView.findViewById(R.id.name2);
            score2 = itemView.findViewById(R.id.score2);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    ScoreTableRow getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }


}