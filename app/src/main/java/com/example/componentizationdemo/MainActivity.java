package com.example.componentizationdemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Printer;
import android.view.View;
import android.widget.ImageView;

import com.example.componentizationdemo.annotation.ARouter;
import com.example.componentizationdemo.annotation.Parameter;
import com.example.componentizationdemo.api.ARouterManager;
import com.example.componentizationdemo.api.ParameterManager;
import com.example.componentizationdemo.common.Cons;
import com.example.componentizationdemo.common.order.OrderDrawable;

@ARouter(path = "/app/MainActivity")
public class MainActivity extends AppCompatActivity {

    @Parameter(name = "/order/getDrawable")
    OrderDrawable orderDrawable;

    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = findViewById(R.id.img);
        ParameterManager.getInstance().loadParameter(this);
        img.setImageResource(orderDrawable.getDrawable());
        
    }

    public void jumpPersonal(View view) {

    }

    public void jumpOrder(View view) {
        ARouterManager.getInstance()
                .build("/order/Order_MainActivity")
                .withString("username", "simon")
                .navigation(this, 163);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Log.e(Cons.TAG, data.getStringExtra("call"));
        }
    }

}
