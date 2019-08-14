package com.example.componentizationdemo.order;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.componentizationdemo.annotation.ARouter;


@ARouter(path = "/order/Order_MainActivity")
public class Order_MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity_main);
    }

    public void jumpApp(View view) {
    }

    public void jumpPersonal(View view) {
//        ARouterLoadGroup group = new ARouter$$Group$$personal();
//        Map<String, Class<? extends ARouterLoadPath>> map = group.loadGroup();
//        // 通过order组名获取对应路由路径对象
//        Class<? extends ARouterLoadPath> clazz = map.get("order");
//
//        try {
//            // 类加载动态加载路由路径对象
//            ARouter$$Path$$order path = (ARouter$$Path$$order) clazz.newInstance();
//            Map<String, RouterBean> pathMap = path.loadPath();
//            // 获取目标对象封装
//            RouterBean bean = pathMap.get("/order/Order_MainActivity");
//
//            if (bean != null) {
//                startActivity(new Intent(this, bean.getClazz()));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
