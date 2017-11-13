package com.xianren.guchangyou.myrecyclerview.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guchangyou on 2017/11/13.
 */

public class ModeImpl implements  ModelInterface {
    List<String>list;
    @Override
    public List<?> getData() {
        list=new ArrayList<>();
        for(int i=0;i<20;i++)
        {
            list.add("item"+i);
        }
        return list;
    }
}
