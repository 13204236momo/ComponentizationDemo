package com.example.componentizationdemo.order;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.componentizationdemo.annotation.ARouter;
import com.example.componentizationdemo.annotation.Parameter;
import com.example.componentizationdemo.api.ARouterManager;
import com.example.componentizationdemo.api.ParameterManager;
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
        Log.e(Cons.TAG,"order接收参数值"+name);
    }

    public void jumpApp(View view) {
        ARouterManager.getInstance()
                .build("/app/MainActivity")
                .withResultString("call", "I'am comeback!")
                .navigation(this);

    }

    public void jumpPersonal(View view) {
        ARouterManager.getInstance()
                .build("/personal/Personal_MainActivity")
                .withString("name","personal")
                .navigation(this,163);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Log.e(Cons.TAG, data.getStringExtra("call"));
        }
    }
}
