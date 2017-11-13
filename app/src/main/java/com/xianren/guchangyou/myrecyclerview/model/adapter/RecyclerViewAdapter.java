package com.xianren.guchangyou.myrecyclerview.model.adapter;

import android.provider.SyncStateContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xianren.guchangyou.myrecyclerview.R;
import com.xianren.guchangyou.myrecyclerview.model.OnLoadMore;
import com.xianren.guchangyou.myrecyclerview.presenter.MyPresenter;

import java.util.ArrayList;
import java.util.List;

import base.Constants;

import static base.Constants.TYPE_FOOTER;
import static base.Constants.TYPE_NORMAL;

/**
 * Created by guchangyou on 2017/11/13.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter implements OnLoadMore {
    public List<String> data;
    MyPresenter myPresenter;
    static int page = 1;
    public RecyclerViewAdapter(MyPresenter myPresenter) {
        this.myPresenter = myPresenter;
        data = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_FOOTER) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_view_sample,parent, false);
            FooterViewHolder myViewHolder = new FooterViewHolder(view);
            return myViewHolder;
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;

        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).setData(data.get(position).toString());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position >= data.size()) {
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        return data.size() + 1;
    }


    public OnLoadMore setLoadMoreListen() {
        return this;

    }

    public void addData(List<String> list) {
        data.addAll(list);
    }

    @Override
    public void onLoad() {
        page++;
        myPresenter.getData(page);
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

    class FooterViewHolder extends RecyclerView.ViewHolder {
        View view;

        public FooterViewHolder(View itemView) {
            super(itemView);
            view = itemView;
        }

        public void setData(String str) {
            ((TextView) view.findViewById(R.id.title)).setText(str);
        }
    }
}
