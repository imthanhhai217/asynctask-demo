package com.jaroid.asynctask;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ContentViewHolder> {

    private ArrayList<Content> mListData;

    public ContentAdapter(ArrayList<Content> listData) {
        this.mListData = listData;
    }

    public void updateData(ArrayList<Content> listData) {
        this.mListData = listData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_contet, parent, false);
        return new ContentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContentViewHolder holder, int position) {
        Content content = mListData.get(position);
        holder.tvTitle.setText(content.getTitle());
        holder.tvBody.setText(content.getBody());
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public class ContentViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvBody;

        public ContentViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvBody = itemView.findViewById(R.id.tvBody);
        }
    }
}
