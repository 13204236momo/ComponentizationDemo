package com.example.componentizationdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.componentizationdemo.annotation.ARouter;
import com.example.componentizationdemo.annotation.model.RouterBean;
import com.example.componentizationdemo.api.core.ARouterLoadGroup;
import com.example.componentizationdemo.api.core.ARouterLoadPath;
import com.example.componentizationdemo.apt.ARouter$$Group$$order;
import com.example.componentizationdemo.apt.ARouter$$Group$$personal;
import com.example.componentizationdemo.apt.ARouter$$Path$$order;
import com.example.componentizationdemo.apt.ARouter$$Path$$personal;

import java.util.Map;

@ARouter(path = "/app/MainActivity")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    }

    public void jumpPersonal(View view) {
        ARouterLoadGroup group = new ARouter$$Group$$personal();
        Map<String, Class<? extends ARouterLoadPath>> map = group.loadGroup();
        // 通过order组名获取对应路由路径对象
        Class<? extends ARouterLoadPath> clazz = map.get("personal");

        try {
            // 类加载动态加载路由路径对象
            ARouter$$Path$$personal path = (ARouter$$Path$$personal) clazz.newInstance();
            Map<String, RouterBean> pathMap = path.loadPath();
            // 获取目标对象封装
            RouterBean bean = pathMap.get("/personal/Personal_MainActivity");

            if (bean != null) {
                startActivity(new Intent(this, bean.getClazz()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void jumpOrder(View view) {
        // 最终集成化模式，所有子模块app/order/personal通过APT生成的类文件都会打包到apk里面，不用担心找不到
        ARouterLoadGroup group = new ARouter$$Group$$order();
        Map<String, Class<? extends ARouterLoadPath>> map = group.loadGroup();
        // 通过order组名获取对应路由路径对象
        Class<? extends ARouterLoadPath> clazz = map.get("order");

        try {
            // 类加载动态加载路由路径对象
            ARouter$$Path$$order path = (ARouter$$Path$$order) clazz.newInstance();
            Map<String, RouterBean> pathMap = path.loadPath();
            // 获取目标对象封装
            RouterBean bean = pathMap.get("/order/Order_MainActivity");

            if (bean != null) {
                startActivity(new Intent(this, bean.getClazz()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
