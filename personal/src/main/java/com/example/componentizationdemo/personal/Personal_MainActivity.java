package com.example.componentizationdemo.personal;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.componentizationdemo.annotation.ARouter;
import com.example.componentizationdemo.annotation.Parameter;
import com.example.componentizationdemo.api.ARouterManager;
import com.example.componentizationdemo.api.ParameterManager;
import com.example.componentizationdemo.common.Cons;


@ARouter(path = "/personal/Personal_MainActivity")
public class Personal_MainActivity extends AppCompatActivity {

    @Parameter
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_activity_main);

        //懒加载方式跳转到哪加载哪个类
        ParameterManager.getInstance().loadParameter(this);
        Log.e(Cons.TAG,"personal接收参数值"+name);
    }

    public void jumpApp(View view) {
        ARouterManager.getInstance()
                .build("/app/MainActivity")
                .withString("name","app")
                .navigation(this);
    }

    public void jumpOrder(View view) {
        ARouterManager.getInstance()
                .build("/order/Order_MainActivity")
                .withResultString("call","i am call")
                .navigation(this);
    }
}
