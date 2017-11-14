package com.xianren.guchangyou.myrecyclerview;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.xianren.guchangyou.myrecyclerview.model.OnLoadMore;
import com.xianren.guchangyou.myrecyclerview.View.ViewListener;
import com.xianren.guchangyou.myrecyclerview.model.adapter.RecyclerViewAdapter;
import com.xianren.guchangyou.myrecyclerview.presenter.MyPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewListener {
    RecyclerView myRecyclerView;
    MyPresenter myPresenter;
    RecyclerViewAdapter recyclerViewAdapter;
    OnLoadMore onLoadMore;
    public static boolean isScrollDown = false;
    public static boolean isRefresh = false;
    float startY=0,endY=0,movY=0;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myRecyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);
        myPresenter = new MyPresenter();
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(linearLayoutManager);
        myPresenter.setListener(this);
        recyclerViewAdapter = new RecyclerViewAdapter(myPresenter);
        onLoadMore = recyclerViewAdapter.setLoadMoreListen();
        myRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction())
                {
                    case  MotionEvent.ACTION_DOWN:
                        startY=event.getY();
                        break;
                    case  MotionEvent.ACTION_MOVE:
                        endY=event.getY();
                        movY=endY-startY;
                      int pos=linearLayoutManager.findFirstVisibleItemPosition();
                        if(movY>0 && pos==0)
                        {
                            Log.i("状态","正在下拉"+movY);
                            isRefresh=true;
                        }
                        else
                            isRefresh=false;
                        break;
                    case  MotionEvent.ACTION_UP:
                        if(movY>0 && isRefresh)
                        Toast.makeText(MainActivity.this,"放开刷新 ",Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });

        myRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {// RecyclerView已经停止滑动
                    int lastVisibleItem;
                    // 获取RecyclerView的LayoutManager
                    RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                    // 获取到最后一个可见的item
                    if (layoutManager instanceof LinearLayoutManager) {// 如果是LinearLayoutManager
                        lastVisibleItem = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                    } else if (layoutManager instanceof StaggeredGridLayoutManager) {// 如果是StaggeredGridLayoutManager
                        int[] into = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];
                        ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(into);
                        lastVisibleItem = findMax(into);
                    } else {// 否则抛出异常
                        throw new RuntimeException("Unsupported LayoutManager used");
                    }
                    // 获取item的总数
                    int totalItemCount = layoutManager.getItemCount();
            /*
                并且最后一个可见的item为最后一个item
                并且是向下滑动
             */
                    if (lastVisibleItem >= totalItemCount - 1 && isScrollDown) {
                        // 此处调用加载更多回调接口的回调方法
                        // 触发加载更多的回调方法
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                onLoadMore.onLoad();
                            }
                        }, 1500);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                isScrollDown = dy > 0;
            }
        });
        myRecyclerView.setAdapter(recyclerViewAdapter);
        innitData();
    }

    @Override
    public void refreshUi(final List<String> list) {
        recyclerViewAdapter.addData(list);
        recyclerViewAdapter.notifyDataSetChanged();


    }

    public void innitData() {
        myPresenter.getData(1);

    }

    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }


}
