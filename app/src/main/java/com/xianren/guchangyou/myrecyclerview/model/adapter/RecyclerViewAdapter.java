package com.xianren.guchangyou.myrecyclerview.model.adapter;

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

import static base.Constants.TYPE_FOOTER;
import static base.Constants.TYPE_HEADER;
import static base.Constants.TYPE_NORMAL;

/**
 * Created by guchangyou on 2017/11/13.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter implements OnLoadMore {
    public List<String> data;
    MyPresenter myPresenter;
    static int page = 1;
    private ArrayList<View> mHeaderViews;
    public RecyclerViewAdapter(MyPresenter myPresenter,ArrayList<View> headerViews) {
        this.myPresenter = myPresenter;
        data = new ArrayList<>();
        this.mHeaderViews = headerViews;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TYPE_HEADER:
                FooterViewHolder header = new FooterViewHolder(mHeaderViews.get(0));
                return header;
            case TYPE_FOOTER:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_view_sample, parent, false);
                FooterViewHolder footerViewHolder = new FooterViewHolder(view);
                return footerViewHolder;
            case TYPE_NORMAL:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
                MyViewHolder myViewHolder = new MyViewHolder(view);
                return myViewHolder;
            default:
                return null;
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
        if (position == 0) {
            return TYPE_HEADER;
        }
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
