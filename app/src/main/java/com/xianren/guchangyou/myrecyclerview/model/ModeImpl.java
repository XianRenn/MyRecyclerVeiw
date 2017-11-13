package com.xianren.guchangyou.myrecyclerview.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guchangyou on 2017/11/13.
 */

public class ModeImpl implements ModelInterface {
    List<String> list;
    private static int tem = 0;

    @Override
    public List<?> getData(int page) {
        list = new ArrayList<>();
        for (int i = tem; i < page * 15; i++) {
            list.add("item" + i);
        }
        tem = page * 15;
        return list;
    }
}
