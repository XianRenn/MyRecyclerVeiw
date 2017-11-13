package com.xianren.guchangyou.myrecyclerview.model.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xianren.guchangyou.myrecyclerview.R;

import java.util.List;

/**
 * Created by guchangyou on 2017/11/13.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter {
    List<?> data;

    public RecyclerViewAdapter(List<?> data) {
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder) holder).setData(data.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        View view;

        public MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
        }

        public void setData(String str) {
            ((TextView) view.findViewById(R.id.title)).setText(str);
        }
    }
}
