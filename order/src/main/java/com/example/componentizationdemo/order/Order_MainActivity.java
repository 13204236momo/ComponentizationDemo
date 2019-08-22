package com.example.componentizationdemo.order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.componentizationdemo.annotation.ARouter;
import com.example.componentizationdemo.annotation.Parameter;
import com.example.componentizationdemo.api.core.ARouterManager;
import com.example.componentizationdemo.api.core.ParameterManager;
import com.example.componentizationdemo.common.Cons;


@ARouter(path = "/order/Order_MainActivity")
public class Order_MainActivity extends AppCompatActivity {

    @Parameter
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity_main);

        //懒加载方式跳转到哪加载哪个类
        ParameterManager.getInstance().loadParameter(this);
        Log.e(Cons.TAG,"接收参数值"+name);
    }

    public void jumpApp(View view) {
    }

    public void jumpPersonal(View view) {

        ARouterManager.getInstance().build("/personal/Personal_MainActivity")
                .withString("name","kala")
                .navigation(this);

    }
}
