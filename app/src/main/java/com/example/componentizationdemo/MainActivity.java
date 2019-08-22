package com.example.componentizationdemo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.componentizationdemo.annotation.ARouter;
import com.example.componentizationdemo.order.Order_MainActivity;

import java.util.Map;

@ARouter(path = "/app/MainActivity")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    }

    public void jumpPersonal(View view) {

    }

    public void jumpOrder(View view) {
          Intent intent = new Intent(this, Order_MainActivity.class);
          intent.putExtra("name","kala");
          startActivity(intent);
    }
}
