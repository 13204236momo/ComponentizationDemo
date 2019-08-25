package com.example.componentizationdemo.order.impl;

import com.example.componentizationdemo.annotation.ARouter;
import com.example.componentizationdemo.common.order.OrderDrawable;
import com.example.componentizationdemo.order.R;

@ARouter(path = "/order/getDrawable")
public class OrderDrawableImpl implements OrderDrawable {
    @Override
    public int getDrawable() {
        return R.drawable.ic_alarm_add_black_24dp;
    }
}
