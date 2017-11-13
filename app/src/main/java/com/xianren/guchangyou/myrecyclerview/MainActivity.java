package com.xianren.guchangyou.myrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xianren.guchangyou.myrecyclerview.View.ViewListener;
import com.xianren.guchangyou.myrecyclerview.model.adapter.RecyclerViewAdapter;
import com.xianren.guchangyou.myrecyclerview.presenter.MyPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewListener {
    RecyclerView myRecyclerView;
    MyPresenter myPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myRecyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);
        myPresenter = new MyPresenter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(linearLayoutManager);
        getData();
    }

    public void getData() {
        myPresenter.setListener(this);
        myPresenter.getData();

    }


    @Override
    public void refreshUi(List<?> list) {
        RecyclerViewAdapter adapter=new RecyclerViewAdapter(list);
          myRecyclerView.setAdapter(adapter);

    }
}
