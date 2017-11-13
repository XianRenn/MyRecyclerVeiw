package com.xianren.guchangyou.myrecyclerview.presenter;

import com.xianren.guchangyou.myrecyclerview.View.ViewListener;
import com.xianren.guchangyou.myrecyclerview.model.ModeImpl;
import com.xianren.guchangyou.myrecyclerview.model.ModelInterface;

import java.util.List;

/**
 * Created by guchangyou on 2017/11/13.
 */

public class MyPresenter {
    ModelInterface modelInterface;
    ViewListener myListener;

    public void getData(int page) {
        modelInterface = new ModeImpl();
        List<String> list = (List<String>) modelInterface.getData(page);
        myListener.refreshUi(list);
    }

    public void setListener(ViewListener myListener) {
        this.myListener=myListener;

    }
}
